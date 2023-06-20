package com.mycompany.departamentojdbc.dao;

import com.mycompany.departamentojdbc.modelo.Departamento;
import com.mycompany.departamentojdbc.modelo.Vendedor;
import java.util.List;

public interface VendedorDao {

    void insert(Vendedor vendedor);

    void update(Vendedor vendedor);

    void deleteById(Integer id);

    Vendedor findById(Integer id);

    List<Vendedor> findAll();
    
    List<Vendedor> findByDepartamento(Departamento departamento);

}
