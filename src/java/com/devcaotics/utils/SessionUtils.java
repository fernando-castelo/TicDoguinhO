/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.utils;

import com.devcaotics.controllers.LoginController;
import com.devcaotics.controllers.TutorController;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nando
 */
public class SessionUtils {
    
  public static LoginController getLoginController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        return (LoginController) session.getAttribute("loginController");
    }
  
  public static TutorController getTutorController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        return (TutorController) session.getAttribute("tutorController");
  }
}
