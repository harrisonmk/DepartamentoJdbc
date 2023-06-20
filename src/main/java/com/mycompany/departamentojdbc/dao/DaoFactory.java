package com.mycompany.departamentojdbc.dao;

import com.mycompany.departamentojdbc.dao.impl.DepartamentoDaoJdbc;
import com.mycompany.departamentojdbc.dao.impl.VendedorDaoJdbc;
import com.mycompany.departamentojdbc.db.DB;

public class DaoFactory {

    public static VendedorDao CriaVendedorDao() {

        return new VendedorDaoJdbc(DB.getConnection());

    }

    
    
    public static DepartamentoDao criaDepartamentoDao() {

        return new DepartamentoDaoJdbc(DB.getConnection());
    }

}
