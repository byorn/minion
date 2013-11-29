/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package softwareperson.framework.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import softwareperson.framework.dao.exceptions.NonexistentEntityException;
import softwareperson.framework.dtos.SearchTaskDTO;
import softwareperson.framework.entities.Task;
import softwareperson.framework.util.RecordStatus;


/**
 *
 * @author 260514b
 */
public class TaskDAO implements Serializable {

    EntityManagerFactory factory = null;
    
    private static TaskDAO instance = new TaskDAO();
    
    private TaskDAO(){};
    
    public static TaskDAO instance(){
        return instance;
    }

    public EntityManager getEntityManager() {
    
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("test");
            return factory.createEntityManager();
        }
        return factory.createEntityManager();
        
        
    }

    public void create(Task task) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            task.setStatus(RecordStatus.ACT.toString());
            em.persist(task);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Task task) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
           
            em.getTransaction().begin();
            task.setStatus(RecordStatus.ACT.toString());
            em.merge(task);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task task;
            try {
                task = em.getReference(Task.class, id);
                task.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The task with id " + id + " no longer exists.", enfe);
            }
            em.remove(task);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Task> findTaskEntities() {
        return findTaskEntities(true, -1, -1);
    }

    public List<Task> findTaskEntities(int maxResults, int firstResult) {
        return findTaskEntities(false, maxResults, firstResult);
    }

    private List<Task> findTaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Task as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Task findTask(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaskCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Task as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Task> findTaskEntities(SearchTaskDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Task as o where o.name like :name");
      
           return q.setParameter("name","%" + searchDTO.getName() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
