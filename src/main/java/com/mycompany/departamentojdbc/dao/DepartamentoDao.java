package com.mycompany.departamentojdbc.dao;

import com.mycompany.departamentojdbc.modelo.Departamento;
import java.util.List;

public interface DepartamentoDao {

    void insert(Departamento departamento);

    void update(Departamento departamento);

    void deleteById(Integer id);

    Departamento findById(Integer id);
    
    List<Departamento> findAll();

}
