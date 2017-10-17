/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tiago
 */
public abstract class GenericDAO<T> implements Serializable{
    protected EntityManagerFactory emf;
    protected EntityManager em;
    
    protected void open(){
        emf = Persistence.createEntityManagerFactory("WebHeroesPU");
        em = emf.createEntityManager();
    }
    
    protected void close(){
        em.close();
        emf.close();
    }
    
    public void save(T object){
        try{
            this.open();
            this.em.getTransaction().begin();
            this.em.merge(object);
            this.em.getTransaction().commit();
        }finally{
            this.close();
        }
    }
    
    
}
