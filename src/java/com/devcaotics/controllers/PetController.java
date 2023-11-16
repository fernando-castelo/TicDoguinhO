/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Pet;
import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.utils.SessionUtils;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nando
 */

@ManagedBean
@ViewScoped
public class PetController {
    
    private Pet cadastro;
    private Pet selecionado;
    
    private EntityManager entityManager;

    public PetController() {
        this.cadastro = new Pet();
        this.selecionado = new Pet();
    }
    
    public List<Pet> readAll() {
        return ManagerDao.getCurrentInstance().read("Select p from Pet p", Pet.class);
    }
    
//    public List<Pet> getTutorPets() {
//        
////        LoginController login = SessionUtils.getLoginController();
////        
////        String jpql = "SELECT pet FROM Pet pet JOIN pet.tutors tutor WHERE tutor.codigo = :tutorCodigo";
////        Query query = entityManager.createQuery(jpql, Pet.class);
////        query.setParameter("tutorCodigo", login.getLogado().getCodigo());
////        
////        return query.getResultList();
//    }
    

    
    public void insert() {
        
       LoginController tutor = SessionUtils.getLoginController();
        
       this.cadastro.addTutor(tutor.getLogado());
          
       ManagerDao.getCurrentInstance().insert(this.cadastro);
      
       tutor.getLogado().addPet(cadastro);
       
       ManagerDao.getCurrentInstance().update(tutor.getLogado());
      
       this.cadastro = new Pet();
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pet cadastrado com sucesso!"));
    }
    
      public void update() {
          ManagerDao.getCurrentInstance().update(this.selecionado);
          
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pet atualizado com sucesso!"));
      }
      
    
      public Pet getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Pet selecionado) {
        this.selecionado = selecionado;
    }
    
    
    public Pet getCadastro() {
        return cadastro;
    }

    public void setCadastro(Pet cadastro) {
        this.cadastro = cadastro;
    }
    
}
