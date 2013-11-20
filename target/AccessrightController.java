/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareperson.framework.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import  softwareperson.framework.dao.AccessrightDAO;
import softwareperson.framework.dtos.SearchAccessrightDTO;
import softwareperson.framework.entities.Accessright;

/**
 *
 * @author byorn
 */
@ManagedBean
@RequestScoped
public class AccessrightController {

    private Collection<Accessright> accessrights = null;

    private Accessright accessright = null;
    
    private SearchAccessrightDTO searchAccessright = null;

    

    public AccessrightController() {

        accessrights = new ArrayList<Accessright>();
        accessrights.addAll(AccessrightDAO.instance().findAccessrightEntities());
        
        if(accessright==null){
            accessright = new Accessright();
        }
        
        if(searchAccessright==null){
            searchAccessright = new SearchAccessrightDTO();
        }

    }

    public Collection<Accessright> getAccessrights() {
        return accessrights;
    }

    public void setAccessrights(Collection<Accessright> accessrights) {
        this.accessrights = accessrights;
    }

    public Accessright getAccessright() {
        return accessright;
    }

    public void setAccessright(Accessright accessright) {
        this.accessright = accessright;
    }
    
    public SearchAccessrightDTO getSearchAccessright() {
        return searchAccessright;
    }

    public void setSearchAccessright(SearchAccessrightDTO searchAccessright) {
        this.searchAccessright = searchAccessright;
    }

    
    
    public String create() {
        AccessrightDAO.instance().create(accessright);
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            AccessrightDAO.instance().edit(accessright);
        } catch (Exception ex) {
            Logger.getLogger(AccessrightController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(searchAccessright!=null){
             accessrights.clear();
            accessrights.addAll(AccessrightDAO.instance().findAccessrightEntities(searchAccessright));
         }
         return "";
     }
     
     

}
