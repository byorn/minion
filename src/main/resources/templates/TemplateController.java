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

    private Collection<{Entity}> objs = null;

    private {Entity} obj = null;
    
    private Search{Entity}DTO search{Entity} = null;

    

    public {Entity}Controller() {

        objs = new ArrayList<{Entity}>();
        objs.addAll({Entity}DAO.instance().find{Entity}Entities());
        
        if(obj==null){
            obj = new {Entity}();
        }
        
        if(search{Entity}==null){
            search{Entity} = new Search{Entity}DTO();
        }

    }

    public Collection<{Entity}> get{Entity}s() {
        return objs;
    }

    public void set{Entity}s(Collection<{Entity}> objs) {
        this.objs = objs;
    }

    public {Entity} get{Entity}() {
        return obj;
    }

    public void set{Entity}({Entity} obj) {
        this.obj = obj;
    }
    
    public Search{Entity}DTO getSearch{Entity}() {
        return search{Entity};
    }

    public void setSearch{Entity}(Search{Entity}DTO search{Entity}) {
        this.search{Entity} = search{Entity};
    }

    
    
    public String create() {
        {Entity}DAO.instance().create(obj);
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            {Entity}DAO.instance().edit(obj);
        } catch (Exception ex) {
            Logger.getLogger({Entity}Controller.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(search{Entity}!=null){
             objs.clear();
            objs.addAll({Entity}DAO.instance().find{Entity}Entities(search{Entity}));
         }
         return "";
     }
     
     

}
