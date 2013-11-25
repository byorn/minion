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
import softwareperson.framework.dtos.SearchPriorityDTO;
import softwareperson.framework.entities.Priority;
import softwareperson.framework.util.RecordStatus;


/**
 *
 * @author 260514b
 */
public class PriorityDAO implements Serializable {

    EntityManagerFactory factory = null;
    
    private static PriorityDAO instance = new PriorityDAO();
    
    private PriorityDAO(){};
    
    public static PriorityDAO instance(){
        return instance;
    }

    public EntityManager getEntityManager() {
    
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("test");
            return factory.createEntityManager();
        }
        return factory.createEntityManager();
        
        
    }

    public void create(Priority priority) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            priority.setStatus(RecordStatus.ACT.toString());
            em.persist(priority);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Priority priority) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
           
            em.getTransaction().begin();
            priority.setStatus(RecordStatus.ACT.toString());
            em.merge(priority);
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
            Priority priority;
            try {
                priority = em.getReference(Priority.class, id);
                priority.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The priority with id " + id + " no longer exists.", enfe);
            }
            em.remove(priority);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Priority> findPriorityEntities() {
        return findPriorityEntities(true, -1, -1);
    }

    public List<Priority> findPriorityEntities(int maxResults, int firstResult) {
        return findPriorityEntities(false, maxResults, firstResult);
    }

    private List<Priority> findPriorityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Priority as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Priority findPriority(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Priority.class, id);
        } finally {
            em.close();
        }
    }

    public int getPriorityCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Priority as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Priority> findPriorityEntities(SearchPriorityDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Priority as o where o.name like :name");
      
           return q.setParameter("name","%" + searchDTO.getName() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
