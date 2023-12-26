/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Postagem;
import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.utils.SessionUtils;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author Nando
 */
@ManagedBean
@ViewScoped
public class PostagemController {
    
    private Postagem cadastro;
    private Postagem selecionado;
    
    private EntityManager entityManager;
    
    public PostagemController() {
        this.cadastro = new Postagem();
        this.selecionado = new Postagem();
    }
    
    public List<Postagem> readAll() {
        return ManagerDao.getCurrentInstance().read("SELECT p FROM Postagem p", Postagem.class);
    }
    
    public void insert() {
       
       PetController pet = SessionUtils.getPetController();
        
       this.cadastro.setPet(pet.getSelecionado());
       
       ManagerDao.getCurrentInstance().insert(this.cadastro);
       
       pet.getSelecionado().addPostagem(this.cadastro);
       
       ManagerDao.getCurrentInstance().update(pet.getSelecionado());
       
       this.cadastro = new Postagem();
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Postagem cadastrada com sucesso!"));
        
    }
    

    public Postagem getCadastro() {
        return cadastro;
    }

    public void setCadastro(Postagem cadastro) {
        this.cadastro = cadastro;
    }

    public Postagem getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Postagem selecionado) {
        this.selecionado = selecionado;
    }
    
    
    
}
