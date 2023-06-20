package com.mycompany.departamentojdbc;

import com.mycompany.departamentojdbc.dao.DaoFactory;
import com.mycompany.departamentojdbc.dao.DepartamentoDao;
import com.mycompany.departamentojdbc.dao.VendedorDao;
import com.mycompany.departamentojdbc.modelo.Departamento;
import com.mycompany.departamentojdbc.modelo.Vendedor;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DepartamentoJdbc {

    public static void main(String[] args) {

        /*   VendedorDao vendedorDao = DaoFactory.CriaVendedorDao();

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
          System.out.println("DELETE COMPLETO");  */
        Scanner sc = new Scanner(System.in);

        DepartamentoDao departmentDao = DaoFactory.criaDepartamentoDao();

        System.out.println("=== TEST 1: findById =======");
        Departamento dep = departmentDao.findById(1);
        System.out.println(dep);

        
        
        System.out.println("\n=== TEST 2: findAll =======");
        List<Departamento> list = departmentDao.findAll();
        for (Departamento d : list) {
            System.out.println(d);
        }
        

        System.out.println("\n=== TEST 3: insert =======");
        Departamento newDepartment = new Departamento(null, "Music");
        departmentDao.insert(newDepartment);
        System.out.println("INSERIDO! Novo ID: " + newDepartment.getId());

        
        
        System.out.println("\n=== TEST 4: update =======");
        Departamento dep2 = departmentDao.findById(1);
        dep2.setNome("Food");
        departmentDao.update(dep2);
        System.out.println("Update COMPLETO");

        
        
        System.out.println("\n=== TEST 5: delete =======");
        System.out.print("Entre com o ID para deletar: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete COMPLETO");

        sc.close();

    }
}
