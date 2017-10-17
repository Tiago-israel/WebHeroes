/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiago
 */
@Entity
@Table(catalog = "superheroes", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Villain.findAll", query = "SELECT v FROM Villain v")
    , @NamedQuery(name = "Villain.findById", query = "SELECT v FROM Villain v WHERE v.id = :id")
    , @NamedQuery(name = "Villain.findByName", query = "SELECT v FROM Villain v WHERE v.name = :name")
    , @NamedQuery(name = "Villain.findByCreationDate", query = "SELECT v FROM Villain v WHERE v.creationDate = :creationDate")
    , @NamedQuery(name = "Villain.findByNatalPlanet", query = "SELECT v FROM Villain v WHERE v.natalPlanet = :natalPlanet")
    , @NamedQuery(name = "Villain.findByPower", query = "SELECT v FROM Villain v WHERE v.power = :power")
    , @NamedQuery(name = "Villain.findByWeakness", query = "SELECT v FROM Villain v WHERE v.weakness = :weakness")
    , @NamedQuery(name = "Villain.findByPhotoName", query = "SELECT v FROM Villain v WHERE v.photoName = :photoName")
    , @NamedQuery(name = "Villain.findByPublisher", query = "SELECT v FROM Villain v WHERE v.publisher = :publisher")})
public class Villain implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false)
    private byte[] photo;
    @Basic(optional = false)
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "natal_planet", nullable = false, length = 45)
    private String natalPlanet;
    @Basic(optional = false)
    @Column(nullable = false)
    private int power;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String weakness;
    @Basic(optional = false)
    @Column(name = "photo_name", nullable = false, length = 255)
    private String photoName;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String publisher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVillain")
    private List<HeroVillain> heroVillainList;

    public Villain() {
    }

    public Villain(Integer id) {
        this.id = id;
    }

    public Villain(Integer id, String name, byte[] photo, Date creationDate, String natalPlanet, int power, String weakness, String photoName, String publisher) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.creationDate = creationDate;
        this.natalPlanet = natalPlanet;
        this.power = power;
        this.weakness = weakness;
        this.photoName = photoName;
        this.publisher = publisher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNatalPlanet() {
        return natalPlanet;
    }

    public void setNatalPlanet(String natalPlanet) {
        this.natalPlanet = natalPlanet;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @XmlTransient
    public List<HeroVillain> getHeroVillainList() {
        return heroVillainList;
    }

    public void setHeroVillainList(List<HeroVillain> heroVillainList) {
        this.heroVillainList = heroVillainList;
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
        if (!(object instanceof Villain)) {
            return false;
        }
        Villain other = (Villain) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Villain[ id=" + id + " ]";
    }
    
}
