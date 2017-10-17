/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tiago
 */
@Entity
@Table(name = "hero_villain", catalog = "superheroes", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HeroVillain.findAll", query = "SELECT h FROM HeroVillain h")
    , @NamedQuery(name = "HeroVillain.findById", query = "SELECT h FROM HeroVillain h WHERE h.id = :id")})
public class HeroVillain implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @JoinColumn(name = "id_hero", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Hero idHero;
    @JoinColumn(name = "id_villain", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Villain idVillain;

    public HeroVillain() {
    }

    public HeroVillain(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hero getIdHero() {
        return idHero;
    }

    public void setIdHero(Hero idHero) {
        this.idHero = idHero;
    }

    public Villain getIdVillain() {
        return idVillain;
    }

    public void setIdVillain(Villain idVillain) {
        this.idVillain = idVillain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HeroVillain)) {
            return false;
        }
        HeroVillain other = (HeroVillain) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HeroVillain[ id=" + id + " ]";
    }
    
}
