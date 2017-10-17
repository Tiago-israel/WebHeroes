/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.Query;
import model.Villain;

/**
 *
 * @author tiago
 */
public class VillainDAO extends GenericDAO<Villain>{

    public VillainDAO() {
        super();
    }
    
    public List<Villain>findAll(){
        try{
            this.open();
            Query query = em.createNamedQuery("Villain.findAll");
            return query.getResultList();
        }finally{
            this.close();
        }
    }
    
}
