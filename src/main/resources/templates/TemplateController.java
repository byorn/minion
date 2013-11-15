/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package {PackageNameStart}.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import  {PackageNameStart}.dao.{Entity}DAO;
import {PackageNameStart}.dtos.Search{Entity}DTO;
import {PackageNameStart}.entities.{Entity};

/**
 *
 * @author byorn
 */
@ManagedBean
@RequestScoped
public class {Entity}Controller {

    private Collection<{Entity}> {entity}s = null;

    private {Entity} {entity} = null;
    
    private Search{Entity}DTO search{Entity} = null;

    

    public {Entity}Controller() {

        {entity}s = new ArrayList<{Entity}>();
        {entity}s.addAll({Entity}DAO.instance().find{Entity}Entities());
        
        if({entity}==null){
            {entity} = new {Entity}();
        }
        
        if(search{Entity}==null){
            search{Entity} = new Search{Entity}DTO();
        }

    }

    public Collection<{Entity}> get{Entity}s() {
        return {entity}s;
    }

    public void set{Entity}s(Collection<{Entity}> {entity}s) {
        this.{entity}s = {entity}s;
    }

    public {Entity} get{Entity}() {
        return {entity};
    }

    public void set{Entity}({Entity} {entity}) {
        this.{entity} = {entity};
    }
    
    public Search{Entity}DTO getSearch{Entity}() {
        return search{Entity};
    }

    public void setSearch{Entity}(Search{Entity}DTO search{Entity}) {
        this.search{Entity} = search{Entity};
    }

    
    
    public String create() {
        {Entity}DAO.instance().create({entity});
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            {Entity}DAO.instance().edit({entity});
        } catch (Exception ex) {
            Logger.getLogger({Entity}Controller.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(search{Entity}!=null){
             {entity}s.clear();
            {entity}s.addAll({Entity}DAO.instance().find{Entity}Entities(search{Entity}));
         }
         return "";
     }
     
     

}
