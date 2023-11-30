/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcaotics.model.dao;

import com.devcaotics.model.Pet;
import com.devcaotics.model.Tutor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nando
 */
public class ManagerDao {
    
    private static ManagerDao myself = null;
    
    public static ManagerDao getCurrentInstance(){
        if(myself == null) {
            myself = new ManagerDao();
        }
        
        return myself;
    }
    
     private EntityManagerFactory emf = null;
            
    private ManagerDao(){
        this.emf = Persistence.createEntityManagerFactory("TicDoguinhOPU");
    } 
    
    public void insert(Object o){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
    
    public void update(Object o){
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
                
        em.merge(o);
        em.getTransaction().commit();
        em.close();
    }
    
    public List read(String query,Class c){
        
        EntityManager em = emf.createEntityManager();
        
        List returnedList = em.createQuery(query,c).getResultList();
        
        em.close();
        
        return returnedList;
    }
    
    public Tutor readTutor(String tutorId) {
        
        EntityManager em = emf.createEntityManager();
            
        Query query = em.createQuery("SELECT t FROM Tutor t WHERE t.codigo = :tutorId");
        query.setParameter("tutorId", Integer.parseInt(tutorId));
        Tutor tutor = (Tutor) query.getSingleResult();
        
        return tutor;
        
    }
    
    
    public Pet readPet(String petId) {
        
        EntityManager em = emf.createEntityManager();
            
        Query query = em.createQuery("SELECT p FROM Pet p WHERE p.codigo = :petId");
        query.setParameter("petId", Integer.parseInt(petId));
        Pet pet = (Pet) query.getSingleResult();
        
        return pet;
    }
    
    public void delete(Object o){
        EntityManager em = emf.createEntityManager();
        
        Object oDelete = o;
        
        if(!em.contains(o)){
            oDelete = em.merge(o);
        }
        em.getTransaction().begin();
        
        em.remove(oDelete);
        em.getTransaction().commit();
        em.close();
    }
    
    public static void main(String args[]){
        
        Tutor t = new Tutor();
        
        t.setEmail("tutortest@gmail.com");
        t.setMamae(false);
        t.setSenha("odeiopinscher");
        t.setUsuario("Robertinho");
        
        getCurrentInstance().insert(t);
        
    }
}
