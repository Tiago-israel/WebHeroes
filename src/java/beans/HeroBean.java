/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.HeroDAO;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Hero;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author tiago
 */
@ManagedBean
@ViewScoped
public class HeroBean implements Serializable {

    /**
     * Creates a new instance of HeroBean
     */
    private final HeroDAO heroDAO;
    private Hero hero;
    private List<Hero> heroes;
    private UploadedFile file;
    private StatusCRUD status;
    private Stream<Hero> heroStream;

    public HeroBean() {
        heroDAO = new HeroDAO();
        this.hero = new Hero();
        this.heroes = new ArrayList<>();
        this.status = StatusCRUD.View;
        this.loadHeroes();
    }

    public void add() {
        this.status = StatusCRUD.Add;
    }

    public void edit(Hero hero) {
        this.hero = hero;
        this.status = StatusCRUD.Add;
    }

    public void save() {
        if (this.file.getContents() != null) {
            this.hero.setPhoto(file.getContents());
            this.hero.setPhotoName(file.getFileName());
        }
        this.heroDAO.save(hero);
        this.clearHero();
        this.loadHeroes();
        this.status = StatusCRUD.View;
    }

    public void filterHeroes(String publisher){
        this.heroes = heroDAO.findAll();
        heroStream = this.heroes.stream();
        this.heroes = heroStream.filter(h -> h.getPublisher().equals(publisher)).collect(Collectors.toList());
    }
    
   
    private void clearHero() {
        this.hero = new Hero();
    }

    private void loadHeroes() {
        List<Hero> localHeroes = heroDAO.findAll();
        this.heroes = new ArrayList<>();
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/heroes");
        try {
            for (Hero hero : localHeroes) {
                FileOutputStream fos = new FileOutputStream(path + "/" + hero.getPhotoName());
                fos.write(hero.getPhoto());
                fos.close();
                this.heroes.add(hero);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    //get & set
    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StatusCRUD getStatus() {
        return status;
    }

    public void setStatus(StatusCRUD status) {
        this.status = status;
    }

}
