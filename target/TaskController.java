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
import  softwareperson.framework.dao.TaskDAO;
import softwareperson.framework.dtos.SearchTaskDTO;
import softwareperson.framework.entities.Task;

/**
 *
 * @author byorn
 */
@ManagedBean
@RequestScoped
public class TaskController {

    private Collection<Task> tasks = null;

    private Task task = null;
    
    private SearchTaskDTO searchTask = null;

    

    public TaskController() {

        tasks = new ArrayList<Task>();
        tasks.addAll(TaskDAO.instance().findTaskEntities());
        
        if(task==null){
            task = new Task();
        }
        
        if(searchTask==null){
            searchTask = new SearchTaskDTO();
        }

    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    public SearchTaskDTO getSearchTask() {
        return searchTask;
    }

    public void setSearchTask(SearchTaskDTO searchTask) {
        this.searchTask = searchTask;
    }

    
    
    public String create() {
        TaskDAO.instance().create(task);
        return "search?faces-redirect=true";
    }
    
     public String edit() {
        try {
            TaskDAO.instance().edit(task);
        } catch (Exception ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, "Error Occurred");
        }
        return "search?faces-redirect=true";
    }
     
     public String search(){
         if(searchTask!=null){
             tasks.clear();
            tasks.addAll(TaskDAO.instance().findTaskEntities(searchTask));
         }
         return "";
     }
     
     

}
