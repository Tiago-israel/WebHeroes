/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.Query;
import model.Hero;

/**
 *
 * @author tiago
 */
public class HeroDAO extends GenericDAO<Hero> {

    public HeroDAO() {
        super();
    }
    
    public List<Hero>findAll(){
        try{
            this.open();
            Query query = em.createNamedQuery("Hero.findAll");
            return query.getResultList();
        }finally{
            this.close();
        }
    }
    
}
