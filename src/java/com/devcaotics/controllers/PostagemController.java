/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Pet;
import com.devcaotics.model.Postagem;
import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.utils.SessionUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.apache.catalina.core.ApplicationPart;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Nando
 */
@ManagedBean
@SessionScoped
public class PostagemController {
    
    private Postagem cadastro;
    private Postagem selecionado;
    private ApplicationPart videoUpload;
    private ApplicationPart tutorVideoUpload;
    
    private EntityManager entityManager;
    
    public PostagemController() {
        this.cadastro = new Postagem();
        this.selecionado = new Postagem();
    }
    
    public List<Postagem> readAll() {
        return ManagerDao.getCurrentInstance().read("SELECT p FROM Postagem p", Postagem.class);
    }
    
    public void insert() throws IOException {
       
       PetController pet = SessionUtils.getPetController();
        
       this.cadastro.setPet(pet.getSelecionado());
       
       if(this.videoUpload != null) {
           
           System.out.println(this.videoUpload.getSubmittedFileName());
           byte[] video = new byte[(int) this.videoUpload.getSize()];
       
           videoUpload.getInputStream().read(video);

           this.cadastro.setVideo(video);
       }  
       
       if(this.tutorVideoUpload != null) {
           System.out.println(this.tutorVideoUpload.getSubmittedFileName());
           byte[] video = new byte[(int) this.tutorVideoUpload.getSize()];
       
           tutorVideoUpload.getInputStream().read(video);

           this.cadastro.setTutorVideo(video);
       }
       
       ManagerDao.getCurrentInstance().insert(this.cadastro);
       
       pet.getSelecionado().addPostagem(this.cadastro);
       
       ManagerDao.getCurrentInstance().update(pet.getSelecionado());
       
       this.cadastro = new Postagem();
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Postagem cadastrada com sucesso!"));
        
    }
    
    public List<Postagem> getPostagensByPet() {
        
        PetController petController = SessionUtils.getPetController();
        
        Set<Postagem> petPostagens = petController.getSelecionado().getPostagens();
        
        List<Postagem> postagemList = new ArrayList<>(petPostagens);

        return postagemList;
    }
        
    public List<Postagem> sortPostagensByDataPublicacao() {
        
        PetController petController = SessionUtils.getPetController();
         
        Pet pet = petController.getSelecionado();
        
        List<Pet> listaSeguindo = pet.getSeguindo();
        
        List<Postagem> listaPostagens = new ArrayList();
        
        for(int i = 0; i < listaSeguindo.size(); i++) {
            listaPostagens.addAll(listaSeguindo.get(i).getPostagens());
        }
        
        return listaPostagens.stream()
                .sorted(Comparator.comparing(Postagem::getDataPublicacao).reversed())
                .collect(Collectors.toList());     
    }
    
    public List<Postagem> sortPostagensPetByDataPublicacao(Pet pet) {
        
        List<Postagem> listaPostagens = new ArrayList<>(pet.getPostagens());
        
        return listaPostagens.stream()
                .sorted(Comparator.comparing(Postagem::getDataPublicacao).reversed())
                .collect(Collectors.toList()); 
    }
    

    
    public List<Postagem> getPostagensPetSeguido(Pet pet) {
        
        Set<Postagem> petPostagens = pet.getPostagens();
        
        List<Postagem> postagemList = new ArrayList<>(petPostagens);

        return postagemList;
    }
    
    public List<Postagem> getPostagensByPetVisualizado() {
        
        PetController petController = SessionUtils.getPetController();
        
        Set<Postagem> petPostagens = petController.getVisualizado().getPostagens();
        
        List<Postagem> postagemList = new ArrayList<>(petPostagens);

        return postagemList;
    }
    
      public void handleFileUpload(FileUploadEvent event) throws IOException {
       
       byte[] video = new byte[(int) event.getFile().getSize()];
       
       event.getFile().getInputstream().read(video);
       
       this.cadastro.setVideo(video);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Imagem Uploidada"));
          
   }
    
     public String getVideoUrl(Postagem postagem) {
        return "/ServeletExibirVideoPostagem?postagemId=" + postagem.getCodigo();
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

    public ApplicationPart getVideoUpload() {
        return videoUpload;
    }

    public void setVideoUpload(ApplicationPart videoUpload) {
        this.videoUpload = videoUpload;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    public ApplicationPart getTutorVideoUpload() {
        return tutorVideoUpload;
    }

    public void setTutorVideoUpload(ApplicationPart tutorVideoUpload) {
        this.tutorVideoUpload = tutorVideoUpload;
    }
     
}
