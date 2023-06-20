package com.mycompany.departamentojdbc;

import com.mycompany.departamentojdbc.dao.DaoFactory;
import com.mycompany.departamentojdbc.dao.VendedorDao;
import com.mycompany.departamentojdbc.modelo.Departamento;
import com.mycompany.departamentojdbc.modelo.Vendedor;
import java.util.Date;
import java.util.List;

public class DepartamentoJdbc {

    public static void main(String[] args) {

        VendedorDao vendedorDao = DaoFactory.CriaVendedorDao();

        System.out.println("=== TESTE 1: Vendedor FindById =====");
        Vendedor vendedor = vendedorDao.findById(3);

        System.out.println(vendedor);
        
        
         System.out.println("\n=== TESTE 2: Vendedor FindByDepartamento =====");
         Departamento departamento = new Departamento(2,null);
         List<Vendedor> lista = vendedorDao.findByDepartamento(departamento);
         

         for(Vendedor obj :lista){
             System.out.println(obj);
         }
         
         
         
         System.out.println("\n=== TESTE 3: Vendedor FindAll =====");
         
          lista = vendedorDao.findAll();
         

         for(Vendedor obj :lista){
             System.out.println(obj);
         }
         
         
         System.out.println("\n=== TESTE 4: Vendedor INSERT =====");
         Vendedor novoVendedor = new Vendedor(null,"Greg","greg@gmail.com",new Date(),4000.0,departamento);
         vendedorDao.insert(novoVendedor);
         System.out.println("INSERIDO! Novo id = "+novoVendedor.getId());
         
         
         System.out.println("\n=== TESTE 5: Vendedor UPDATE =====");
         vendedor = vendedorDao.findById(1);
         vendedor.setNome("Martha Waine");
         vendedorDao.update(vendedor);
         System.out.println("UPDATE COMPLETO");
         
         
          System.out.println("\n=== TESTE 6: Vendedor DELETE =====");
          vendedorDao.deleteById(8);
          System.out.println("DELETE COMPLETO");
         
    }
}
