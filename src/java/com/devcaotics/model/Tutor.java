/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

/**
 *
 * @author Nando
 */

@Entity
public class Tutor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;
    
    private String usuario;
    
    private String email;
    
    private String senha;
    
    private Boolean mamae;
    
    @Lob
    private byte[] imagem;
    
    @ManyToMany
    @JoinTable(
        name = "tutor_pet",
        joinColumns = @JoinColumn(name = "tutor_codigo"),
        inverseJoinColumns = @JoinColumn(name = "pet_codigo")
    )
    private Set<Pet> pets = new HashSet<>();
    
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.getTutors().add(this);
    }
    
     public void removePet(Pet pet) {
        pets.remove(pet);
        pet.getTutors().remove(this);
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getMamae() {
        return mamae;
    }

    public void setMamae(Boolean mamae) {
        this.mamae = mamae;
    }

    public void addPet(Tutor cadastro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
    
}
