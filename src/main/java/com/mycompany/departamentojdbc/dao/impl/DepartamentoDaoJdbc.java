package com.mycompany.departamentojdbc.dao.impl;

import com.mycompany.departamentojdbc.dao.DepartamentoDao;
import com.mycompany.departamentojdbc.db.DB;
import com.mycompany.departamentojdbc.db.DbException;
import com.mycompany.departamentojdbc.db.DbIntegridadeException;
import com.mycompany.departamentojdbc.modelo.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoJdbc implements DepartamentoDao {

    private Connection conexao;

    
    //CONSTRUTOR
    public DepartamentoDaoJdbc(Connection conexao) {

        this.conexao = conexao;

    }

    
    
    @Override
    public void insert(Departamento departamento) {

        PreparedStatement st = null;
        try {
            st = conexao.prepareStatement("INSERT INTO departamento (Nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, departamento.getNome());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    departamento.setId(id);
                }
            } else {
                throw new DbException("Erro Inesperado! Nenhuma Linha Afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    
    
    @Override
    public void update(Departamento departamento) {
        PreparedStatement st = null;
        try {
            st = conexao.prepareStatement("UPDATE departamento SET Nome = ? WHERE Id = ?");

            st.setString(1, departamento.getNome());
            st.setInt(2, departamento.getId());

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
            st = conexao.prepareStatement("DELETE FROM departamento WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegridadeException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    
    
    @Override
    public Departamento findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conexao.prepareStatement("SELECT * FROM departamento WHERE Id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("Id"));
                departamento.setNome(rs.getString("Nome"));
                return departamento;
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
    public List<Departamento> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conexao.prepareStatement("SELECT * FROM departamento ORDER BY Nome");

            rs = st.executeQuery();

            List<Departamento> list = new ArrayList<>();

            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("Id"));
                departamento.setNome(rs.getString("Nome"));
                list.add(departamento);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
    
    

}
