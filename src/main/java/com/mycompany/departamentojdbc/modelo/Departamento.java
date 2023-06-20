
package com.mycompany.departamentojdbc.modelo;

import java.io.Serializable;
import java.util.Objects;


public class Departamento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;

    
    
    public Departamento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    
    
    public Departamento() {
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
    
    

    @Override
    public String toString() {
        return "Id: " + id + 
                "\nNome: " + nome+"\n";
    }
    
    
    
}
