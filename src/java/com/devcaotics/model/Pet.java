/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Nando
 */

@Entity
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;
    
    private String nome;
    
    private String mesAnoNascimento;
    
    private String porte;
    
    private String codigoFoto;
    
    @Lob
    private byte[] imagem;
    
    @ManyToMany(mappedBy = "pets")
    private Set<Tutor> tutors = new HashSet<>();
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Postagem> postagens = new HashSet<>();

    @ManyToMany(mappedBy = "seguindo")
    private List<Pet> seguidores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "pet_seguindo",
        joinColumns = @JoinColumn(name = "seguindo_id"),
        inverseJoinColumns = @JoinColumn(name = "seguidor_id"))
    private List<Pet> seguindo = new ArrayList<>();

    public List<Pet> getSeguidores() {
        return seguidores;
    }

    public List<Pet> getSeguindo() {
        return seguindo;
    }

    public Pet addSeguidor(Pet pet, Pet seguidor) {
        pet.seguidores.add(seguidor);
        return pet;
    }

    public Pet addSeguindo(Pet pet, Pet seguindo) {
        seguindo.seguindo.add(pet);
        return seguindo;
    }
    
    public void addPostagem(Postagem postagem) {
        postagens.add(postagem);
        postagem.setPet(this);
    }

    public void removePostagem(Postagem postagem) {
        postagens.remove(postagem);
        postagem.setPet(null);
    }

    public Set<Postagem> getPostagens() {
        return postagens;
    }
    
    public void addTutor(Tutor tutor) {
        tutors.add(tutor);
        tutor.getPets().add(this);
    }

    public void removeTutor(Tutor tutor) {
        tutors.remove(tutor);
        tutor.getPets().remove(this);
    }

    public Set<Tutor> getTutors() {
        return tutors;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMesAnoNascimento() {
        return mesAnoNascimento;
    }

    public void setMesAnoNascimento(String mesAnoNascimento) {
        this.mesAnoNascimento = mesAnoNascimento;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getCodigoFoto() {
        return codigoFoto;
    }

    public void setCodigoFoto(String codigoFoto) {
        this.codigoFoto = codigoFoto;
    }

    
}
