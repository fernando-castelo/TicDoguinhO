/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Pet;
import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.utils.SessionUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nando
 */

@ManagedBean
@ViewScoped
public class PetController {
    
    private Pet cadastro;

    public PetController() {
        this.cadastro = new Pet();
    }
    
    public void insert() {
        
       LoginController tutor = SessionUtils.getLoginController();
        
       this.cadastro.addTutor(tutor.getLogado());
          
       ManagerDao.getCurrentInstance().insert(this.cadastro);
      
       tutor.getLogado().addPet(cadastro);
       
       ManagerDao.getCurrentInstance().update(tutor.getLogado());
      
       this.cadastro = new Pet();
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pet cadastrado com sucesso!"));
    }
    
    public Pet getCadastro() {
        return cadastro;
    }

    public void setCadastro(Pet cadastro) {
        this.cadastro = cadastro;
    }
    
}
