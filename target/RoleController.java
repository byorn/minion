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
import  softwareperson.framework.dao.RoleDAO;
import softwareperson.framework.dtos.SearchRoleDTO;
import softwareperson.framework.entities.Role;

/**
 *
 * @author byorn
 */
@ManagedBean
@RequestScoped
public class RoleController {

    private Collection<Role> roles = null;

    private Role role = null;
    
    private SearchRoleDTO searchRole = null;

    

    public RoleController() {

        roles = new ArrayList<Role>();
        roles.addAll(RoleDAO.instance().findRoleEntities());
        
        if(role==null){
            role = new Role();
        }
        
        if(searchRole==null){
            searchRole = new SearchRoleDTO();
        }

    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public SearchRoleDTO getSearchRole() {
        return searchRole;
    }

    public void setSearchRole(SearchRoleDTO searchRole) {
        this.searchRole = searchRole;
    }

    
    
    public String create() {
        RoleDAO.instance().create(role);
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            RoleDAO.instance().edit(role);
        } catch (Exception ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(searchRole!=null){
             roles.clear();
            roles.addAll(RoleDAO.instance().findRoleEntities(searchRole));
         }
         return "";
     }
     
     

}
