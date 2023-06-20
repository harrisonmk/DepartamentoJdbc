package com.mycompany.departamentojdbc.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private String email;
    private Date dataAniversario;
    private Double baseSalarial;
    private Departamento departamento;

    public Vendedor(Integer id, String nome, String email, Date dataAniversario, Double baseSalarial, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataAniversario = dataAniversario;
        this.baseSalarial = baseSalarial;
        this.departamento = departamento;
    }

    public Vendedor(Integer id, String nome, String email, Date dataAniversario, Double baseSalarial) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataAniversario = dataAniversario;
        this.baseSalarial = baseSalarial;
    }

    public Vendedor() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public Double getBaseSalarial() {
        return baseSalarial;
    }

    public void setBaseSalarial(Double baseSalarial) {
        this.baseSalarial = baseSalarial;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Vendedor other = (Vendedor) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
    

    @Override
    public String toString() {
        return "id: " + id + 
                "\nNome: " + nome + 
                "\nEmail: " + email + 
                "\nData Aniversario: " + dataAniversario + 
                "\nBase Salarial: " + baseSalarial + 
                "\nDepartamento: \n" + departamento;
    }
    
    
    
    
    

}
