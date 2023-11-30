/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Pet;
import com.devcaotics.model.dao.ManagerDao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author Nando
 */
@WebServlet(name = "ServletExibirImagemPet", urlPatterns = {"/ServletExibirImagemPet"})
public class ServletExibirImagenPet extends HttpServlet {
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletExibirImagemDoguinhoGambiarra</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletExibirImagemDoguinhoGambiarra at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

           String petId = request.getParameter("petId");

           Pet pet = ManagerDao.getCurrentInstance().readPet(petId); 

           byte[] imageData = pet.getImagem();

           response.setContentType("image/jpg");

           try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(imageData);
                outputStream.flush();
            }
    }
    
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
     @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
