/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.VillainDAO;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Villain;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author tiago
 */
@ManagedBean
@ViewScoped
public class VillainBean {

    private final VillainDAO villainDAO;
    private Villain villain;
    private List<Villain> villains;
    private UploadedFile file;
    private StatusCRUD status;
    
    
    public VillainBean() {
        villainDAO = new VillainDAO();
        this.villain = new Villain();
        this.status = StatusCRUD.View;
        loadVillains();
    }
    
    public void add() {
        this.status = StatusCRUD.Add;
    }

    public void edit(Villain villain) {
        this.villain = villain;
        this.status = StatusCRUD.Add;
    }

    public void save() {
        if (this.file.getContents() != null) {
            this.villain.setPhoto(file.getContents());
            this.villain.setPhotoName(file.getFileName());
        }
        this.villainDAO.save(villain);
        this.clearVillain();
        this.loadVillains();
        this.status = StatusCRUD.View;
    }

    private void clearVillain() {
        this.villain = new Villain();
    }
    
    private void loadVillains() {
        List<Villain> localVillains = villainDAO.findAll();
        this.villains = new ArrayList<>();
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/villains");
        try {
            for (Villain villain : localVillains) {
                FileOutputStream fos = new FileOutputStream(path + "/" + villain.getPhotoName());
                fos.write(villain.getPhoto());
                fos.close();
                this.villains.add(villain);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    

    public Villain getVillain() {
        return villain;
    }

    public void setVillain(Villain villain) {
        this.villain = villain;
    }

    public List<Villain> getVillains() {
        return villains;
    }

    public void setVillains(List<Villain> villains) {
        this.villains = villains;
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
