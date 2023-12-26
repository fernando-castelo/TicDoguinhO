/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Tutor;
import com.devcaotics.model.dao.ManagerDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nando
 */
@ManagedBean
@SessionScoped
public class LoginController {
    
    private Tutor logado;
    
    public String logar(String email, String senha) {
        
        try{
            Tutor aux = (Tutor)ManagerDao.getCurrentInstance().read("select t from Tutor t where t.email=\""+email+"\" and t.senha=\""+senha+"\"", Tutor.class).get(0);

            this.logado = aux;

            return "menuTutor.xhtml";
        
        } catch(IndexOutOfBoundsException o) {
            
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao logar",
                    "As informacoes do login estao incorretas"));
                
                return null;
        }
    }
    
    public String logout() {
        this.logado = null;
        return "index.xhtml";
    }

    public Tutor getLogado() {
        return logado;
    }

    public void setLogado(Tutor logado) {
        this.logado = logado;
    }
    
}
