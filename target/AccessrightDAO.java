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
import softwareperson.framework.dtos.SearchAccessrightDTO;
import softwareperson.framework.entities.Accessright;
import softwareperson.framework.util.RecordStatus;


/**
 *
 * @author 260514b
 */
public class AccessrightDAO implements Serializable {

    private static  final EntityManager em = null;
    
    private static AccessrightDAO instance = new AccessrightDAO();
    
    private AccessrightDAO(){};
    
    public static AccessrightDAO instance(){
        return instance;
    }

    public EntityManager getEntityManager() {
    
        if(em == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
            return factory.createEntityManager();
        }
        return em;
        
        
    }

    public void create(Accessright accessright) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            accessright.setStatus(RecordStatus.ACT.toString());
            em.persist(accessright);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accessright accessright) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
           
            em.getTransaction().begin();
            accessright.setStatus(RecordStatus.ACT.toString());
            em.merge(accessright);
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
            Accessright accessright;
            try {
                accessright = em.getReference(Accessright.class, id);
                accessright.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accessright with id " + id + " no longer exists.", enfe);
            }
            em.remove(accessright);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accessright> findAccessrightEntities() {
        return findAccessrightEntities(true, -1, -1);
    }

    public List<Accessright> findAccessrightEntities(int maxResults, int firstResult) {
        return findAccessrightEntities(false, maxResults, firstResult);
    }

    private List<Accessright> findAccessrightEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Accessright as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Accessright findAccessright(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accessright.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccessrightCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Accessright as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Accessright> findAccessrightEntities(SearchAccessrightDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Accessright as o where o.name like :name");
      
           return q.setParameter("name","%" + searchDTO.getName() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
