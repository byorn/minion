/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Company1.Company.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import  Company1.Company.dao.RoleDAO;
import Company1.Company.dtos.SearchRoleDTO;
import Company1.Company.entities.Role;

/**
 *
 * @author byorn
 */
@ManagedBean
@RequestScoped
public class RoleController {

    private Collection<Role> objs = null;

    private Role obj = null;
    
    private SearchRoleDTO searchRole = null;

    

    public RoleController() {

        objs = new ArrayList<Role>();
        objs.addAll(RoleDAO.instance().findRoleEntities());
        
        if(obj==null){
            obj = new Role();
        }
        
        if(searchRole==null){
            searchRole = new SearchRoleDTO();
        }

    }

    public Collection<Role> getRoles() {
        return objs;
    }

    public void setRoles(Collection<Role> objs) {
        this.objs = objs;
    }

    public Role getRole() {
        return obj;
    }

    public void setRole(Role obj) {
        this.obj = obj;
    }
    
    public SearchRoleDTO getSearchRole() {
        return searchRole;
    }

    public void setSearchRole(SearchRoleDTO searchRole) {
        this.searchRole = searchRole;
    }

    
    
    public String create() {
        RoleDAO.instance().create(obj);
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            RoleDAO.instance().edit(obj);
        } catch (Exception ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(searchRole!=null){
             objs.clear();
            objs.addAll(RoleDAO.instance().findRoleEntities(searchRole));
         }
         return "";
     }
     
     

}
