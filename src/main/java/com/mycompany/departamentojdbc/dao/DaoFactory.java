
package com.mycompany.departamentojdbc.dao;

import com.mycompany.departamentojdbc.dao.impl.VendedorDaoJdbc;
import com.mycompany.departamentojdbc.db.DB;


public class DaoFactory {
    
    public static VendedorDao CriaVendedorDao(){
        
        return new VendedorDaoJdbc(DB.getConnection());
        
        
    }
    
}
