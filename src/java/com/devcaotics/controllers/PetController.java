/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.controllers;

import com.devcaotics.model.Pet;
import com.devcaotics.model.Postagem;
import com.devcaotics.model.Tutor;
import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.utils.SessionUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Nando
 */

@ManagedBean
@SessionScoped
public class PetController {
    
    private Pet cadastro;
    private Pet selecionado;
    private Pet visualizado;
    
    private List<Pet> petsEncontrados;
    private String nomeProcurado;
    
    private EntityManager entityManager;

    public PetController() {
        this.cadastro = new Pet();
        this.selecionado = new Pet();
    }
    
    public List<Pet> readAll() {
        return ManagerDao.getCurrentInstance().read("Select p from Pet p", Pet.class);
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
    
    public void adicionarSeguidor(Pet pet) {
        
       Pet petAtualizado = pet.addSeguidor(pet, this.selecionado);
       
       pet.getSeguidores().add(this.selecionado);
       
       Pet petSelecionadoAtualizado = pet.addSeguindo(pet, this.selecionado);
       
       ManagerDao.getCurrentInstance().update(petAtualizado);
       ManagerDao.getCurrentInstance().update(petSelecionadoAtualizado);
       
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pet seguido com sucesso!"));
       
       this.setSelecionado(petSelecionadoAtualizado);
       
    }
    
      public void update() {
          ManagerDao.getCurrentInstance().update(this.selecionado);
          
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pet atualizado com sucesso!"));
      }
      
      
      public void handleFileUpload(FileUploadEvent event) throws IOException {
       
       byte[] im = new byte[(int) event.getFile().getSize()];
       
       event.getFile().getInputstream().read(im);
       
       this.cadastro.setImagem(im);
       
       String randomCodigo = UUID.randomUUID().toString();
       
       this.cadastro.setCodigoFoto(randomCodigo);
       
       ((HttpSession)FacesContext.getCurrentInstance()
               .getExternalContext().getSession(true))
               .setAttribute("imagem", this.cadastro.getImagem());

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put("petImage_" + randomCodigo,
        this.cadastro.getImagem());
       
    }
      
    public String getImageUrl(Pet pet) {
        return "/ServletExibirImagemPet?petId=" + pet.getCodigo();
     }
       
    public String navigateToPetPage(Pet pet) {
        
        this.setSelecionado(pet);
        return "menuPetIndividual.xhtml";
    }  
    
    public String navigateToSearchPage() {
      
        List<Pet> petsEncontrados = ManagerDao.getCurrentInstance().searchPetsByName(this.nomeProcurado);
        this.petsEncontrados = petsEncontrados;
        
        this.nomeProcurado = "";

        return "menuBuscaPets.xhtml?faces-redirect=true";
    }
    public String navigateToSelecionadoPage() {
        
        return "feedPetSelecionado.xhtml";
    }
    
    public String navigateToSearchedPetPage(Pet pet) {
        this.setVisualizado(pet);
        return "menuPetEncontrado.xhtml";
    }
    
    public Tutor getPetTutor(Pet pet) {
        
       Set<Tutor> petTutors = pet.getTutors();
       
       List<Tutor> tutorList = new ArrayList<>(petTutors);
       
       return tutorList.get(0);
    }
    
        
    public List<Pet> getSeguidores() {
        
       return this.selecionado.getSeguidores();
    }
    
    public List<Pet> getSeguindo() {
        return this.selecionado.getSeguindo();
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

    public List<Pet> getPetsEncontrados() {
        return petsEncontrados;
    }

    public void setPetsEncontrados(List<Pet> petsEncontrados) {
        this.petsEncontrados = petsEncontrados;
    }

    public String getNomeProcurado() {
        return nomeProcurado;
    }

    public void setNomeProcurado(String nomeProcurado) {
        this.nomeProcurado = nomeProcurado;
    }

    public Pet getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(Pet visualizado) {
        this.visualizado = visualizado;
    }
    
    
    
}
