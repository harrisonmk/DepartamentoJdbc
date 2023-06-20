package com.mycompany.departamentojdbc.dao.impl;

import com.mycompany.departamentojdbc.dao.VendedorDao;
import com.mycompany.departamentojdbc.db.DB;
import com.mycompany.departamentojdbc.db.DbException;
import com.mycompany.departamentojdbc.modelo.Departamento;
import com.mycompany.departamentojdbc.modelo.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJdbc implements VendedorDao {

    private Connection conexao;

    //Contrutor
    public VendedorDaoJdbc(Connection conexao) {
        this.conexao = conexao;
    }

    
    
    @Override
    public void insert(Vendedor vendedor) {

        PreparedStatement st = null;
        try {
            st = conexao.prepareStatement(
                    "INSERT INTO vendedor "
                    + "(Nome, Email, DataAniversario, BaseSalarial, DepartamentoId) "
                    + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new java.sql.Date(vendedor.getDataAniversario().getTime()));
            st.setDouble(4, vendedor.getBaseSalarial());
            st.setInt(5, vendedor.getDepartamento().getId());

            int linhasModificadas = st.executeUpdate();

            if (linhasModificadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    vendedor.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro Inesperado Nenhuma linha foi Afetada");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);

        }

    }

    
    @Override
    public void update(Vendedor vendedor) {

        PreparedStatement st = null;
        try {
            st = conexao.prepareStatement(
                    "UPDATE vendedor SET "
                    + "Nome = ?, Email = ?, DataAniversario = ?, BaseSalarial = ?, DepartamentoId = ? "
                    + "WHERE Id = ?");

            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new java.sql.Date(vendedor.getDataAniversario().getTime()));
            st.setDouble(4, vendedor.getBaseSalarial());
            st.setInt(5, vendedor.getDepartamento().getId());
            st.setInt(6, vendedor.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);

        }

    }

    
    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;
        try {
            st = conexao.prepareStatement("DELETE FROM vendedor WHERE Id = ?");

            st.setInt(1, id);

            int linhas = st.executeUpdate();
            
            //VERIFICA SE O ID EXISTE PARA EXCLUIR
            if(linhas == 0){
                throw new DbException("NAO PODE SER EXCLUIDO PORQUE O ID NAO EXISTE");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }

    }

    
    
    @Override
    public Vendedor findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conexao.prepareStatement(
                    "SELECT vendedor.*,departamento.Nome as DepNome "
                    + "FROM vendedor INNER JOIN departamento "
                    + "ON vendedor.DepartamentoId = departamento.Id "
                    + "WHERE vendedor.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Departamento dep = instanciandoDepartamento(rs);

                Vendedor vend = instanciandoVendedor(rs, dep);

                return vend;

            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    
    
    @Override
    public List<Vendedor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conexao.prepareStatement(
                    "SELECT vendedor.*,departamento.Nome as DepNome "
                    + "FROM vendedor INNER JOIN departamento "
                    + "ON vendedor.DepartamentoId = departamento.Id "
                    + "ORDER BY Nome");

            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {

                Departamento dep = map.get(rs.getInt("DepartamentoId"));

                if (dep == null) {
                    dep = instanciandoDepartamento(rs);
                    map.put(rs.getInt("DepartamentoId"), dep);
                }

                Vendedor vend = instanciandoVendedor(rs, dep);
                lista.add(vend);

            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    
    
    @Override
    public List<Vendedor> findByDepartamento(Departamento departamento) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conexao.prepareStatement(
                    "SELECT vendedor.*,departamento.Nome as DepNome "
                    + "FROM vendedor INNER JOIN departamento "
                    + "ON vendedor.DepartamentoId = departamento.Id "
                    + "WHERE DepartamentoId = ? "
                    + "ORDER BY Nome");

            st.setInt(1, departamento.getId());
            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {

                Departamento dep = map.get(rs.getInt("DepartamentoId"));

                if (dep == null) {
                    dep = instanciandoDepartamento(rs);
                    map.put(rs.getInt("DepartamentoId"), dep);
                }

                Vendedor vend = instanciandoVendedor(rs, dep);
                lista.add(vend);

            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
    
    

    private Vendedor instanciandoVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor vend = new Vendedor();
        vend.setId(rs.getInt("Id"));
        vend.setNome(rs.getString("Nome"));
        vend.setEmail(rs.getString("Email"));
        vend.setBaseSalarial(rs.getDouble("BaseSalarial"));
        vend.setDataAniversario(rs.getDate("DataAniversario"));
        vend.setDepartamento(dep);
        return vend;
    }

    
    
    private Departamento instanciandoDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartamentoId")); // entre as aspas tem que ser o mesmo nome que esta no banco de dados
        dep.setNome(rs.getString("DepNome"));
        return dep;
    }

    
    
    
    
}
