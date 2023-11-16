 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Tutor;
import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.utils.SessionUtils;
import java.util.List;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Nando
 */
@ManagedBean(name = "tController")
@ViewScoped
public class TutorController {
    
    private Tutor cadastro;
    private Tutor selecionado;
    
    public TutorController() {
        this.cadastro = new Tutor();
        this.selecionado = new Tutor();
    }
    
    public List<Tutor> readAll() {
        return ManagerDao.getCurrentInstance().read("Select t from Tutor t", Tutor.class);
    }
    
   public void insert(String confirma) {
       
       if(!confirma.equals(this.cadastro.getSenha())) {
           
           FacesContext.getCurrentInstance().addMessage("formModal:txtPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validacao", "Usuario cadastrado com sucesso!"));
       }
       
       ManagerDao.getCurrentInstance().insert(this.cadastro);
       
       this.cadastro = new Tutor();
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario cadastrado com sucesso!"));
   }
   
   public void addPetOnTutor(LoginController login) {
       
       login.getLogado().addPet(cadastro);
       
       ManagerDao.getCurrentInstance().update(login.getLogado());
   }
   
   public void update() { 
       
       ManagerDao.getCurrentInstance().update(this.selecionado);
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario atualizado com sucesso!"));
         
   }
   
   public void updateTutorLogado() {
       LoginController login = SessionUtils.getLoginController();
       
       if (login != null) {
           Tutor t = login.getLogado();
           
           ManagerDao.getCurrentInstance().update(this.selecionado);
        }
     }
   
   
   public void updateSenha(String atual, String nova, String confirma) {
       
       LoginController login = SessionUtils.getLoginController();
       
       if(!atual.equals(login.getLogado().getSenha())) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A senha atual esta errada"));
      
           return;
       }
       
       if(!nova.equals(confirma)) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A confirmacao de senha esta incorreta"));
           
           return;
       }
       
       Tutor t = login.getLogado();
       
       t.setSenha(nova);
       
       ManagerDao.getCurrentInstance().update(t);
       
       login.setLogado(null);
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A senha foi alterada com sucesso"));
          
   }
         
 
   public void delete() {
       
       
       ManagerDao.getCurrentInstance().delete(this.selecionado);
      
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario deletado com sucesso!"));
    }

    public Tutor getCadastro() {
        return cadastro;
    }

    public void setCadastro(Tutor cadastro) {
        this.cadastro = cadastro;
    }

    public Tutor getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Tutor selecionado) {
        this.selecionado = selecionado;
    }
   
    
}
