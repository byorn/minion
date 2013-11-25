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
import  softwareperson.framework.dao.PriorityDAO;
import softwareperson.framework.dtos.SearchPriorityDTO;
import softwareperson.framework.entities.Priority;

/**
 *
 * @author byorn
 */
@ManagedBean
@RequestScoped
public class PriorityController {

    private Collection<Priority> prioritys = null;

    private Priority priority = null;
    
    private SearchPriorityDTO searchPriority = null;

    

    public PriorityController() {

        prioritys = new ArrayList<Priority>();
        prioritys.addAll(PriorityDAO.instance().findPriorityEntities());
        
        if(priority==null){
            priority = new Priority();
        }
        
        if(searchPriority==null){
            searchPriority = new SearchPriorityDTO();
        }

    }

    public Collection<Priority> getPrioritys() {
        return prioritys;
    }

    public void setPrioritys(Collection<Priority> prioritys) {
        this.prioritys = prioritys;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public SearchPriorityDTO getSearchPriority() {
        return searchPriority;
    }

    public void setSearchPriority(SearchPriorityDTO searchPriority) {
        this.searchPriority = searchPriority;
    }

    
    
    public String create() {
        PriorityDAO.instance().create(priority);
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            PriorityDAO.instance().edit(priority);
        } catch (Exception ex) {
            Logger.getLogger(PriorityController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(searchPriority!=null){
             prioritys.clear();
            prioritys.addAll(PriorityDAO.instance().findPriorityEntities(searchPriority));
         }
         return "";
     }
     
     

}
