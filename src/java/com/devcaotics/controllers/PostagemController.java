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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    
    public void handleVideoUpload(FileUploadEvent event) throws IOException {
        UploadedFile uploadedVideo = event.getFile();
        byte[] videoData = new byte[(int) uploadedVideo.getSize()];
        uploadedVideo.getInputstream().read(videoData);

        this.cadastro.setVideo(videoData);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vídeo enviado com sucesso!"));
    }
    
     public String getVideoUrl(Postagem postagem) {
        return "/ServletExibirVideoPostagem?petId=" + postagem.getCodigo();
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
