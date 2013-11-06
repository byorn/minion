/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package {packageName};

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import softwareperson.framework.dao.exceptions.NonexistentEntityException;
import softwareperson.framework.dtos.Search{entity}sDTO;
import softwareperson.framework.entities.{entity};


/**
 *
 * @author 260514b
 */
public class {entity}DAO implements Serializable {

    private static  final EntityManager em = null;
    
    private static {entity}DAO instance = new {entity}DAO();
    
    private {entity}DAO(){};
    
    public static {entity}DAO instance(){
        return instance;
    }

    public EntityManager getEntityManager() {
    
        if(em == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
            return factory.createEntityManager();
        }
        return em;
        
        
    }

    public void create({entity} obj) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit({entity} obj) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.merge(obj);
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
            {entity} obj;
            try {
                obj = em.getReference({entity}.class, id);
                obj.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The obj with id " + id + " no longer exists.", enfe);
            }
            em.remove(obj);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<{entity}> find{entity}Entities() {
        return find{entity}Entities(true, -1, -1);
    }

    public List<{entity}> find{entity}Entities(int maxResults, int firstResult) {
        return find{entity}Entities(false, maxResults, firstResult);
    }

    private List<{entity}> find{entity}Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from {entity} as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public {entity} find{entity}(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find({entity}.class, id);
        } finally {
            em.close();
        }
    }

    public int get{entity}Count() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from {entity} as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<{entity}> find{entity}Entities(Search{entity}sDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from {entity} as o where o.{searchfield} like :{searchfield}");
      
           return q.setParameter("{searchfield}","%" + searchDTO.get{searchfield_getter}() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
