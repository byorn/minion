/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Company1.Company.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import Company1.Company.dao.exceptions.NonexistentEntityException;
import Company1.Company.dtos.SearchRoleDTO;
import Company1.Company.entities.Role;
import Company1.Company.util.RecordStatus;


/**
 *
 * @author 260514b
 */
public class RoleDAO implements Serializable {

    private static  final EntityManager em = null;
    
    private static RoleDAO instance = new RoleDAO();
    
    private RoleDAO(){};
    
    public static RoleDAO instance(){
        return instance;
    }

    public EntityManager getEntityManager() {
    
        if(em == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
            return factory.createEntityManager();
        }
        return em;
        
        
    }

    public void create(Role obj) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            obj.setStatus(RecordStatus.ACT.toString());
            em.persist(obj);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Role obj) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
           
            em.getTransaction().begin();
            obj.setStatus(RecordStatus.ACT.toString());
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
            Role obj;
            try {
                obj = em.getReference(Role.class, id);
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

    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Role> findRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Role as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Role findRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoleCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Role as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Role> findRoleEntities(SearchRoleDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Role as o where o.objname like :objname");
      
           return q.setParameter("objname","%" + searchDTO.getRolename() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
