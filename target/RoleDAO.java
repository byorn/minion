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
import softwareperson.framework.dtos.SearchRoleDTO;
import softwareperson.framework.entities.Role;
import softwareperson.framework.util.RecordStatus;


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

    public void create(Role role) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            role.setStatus(RecordStatus.ACT.toString());
            em.persist(role);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Role role) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
           
            em.getTransaction().begin();
            role.setStatus(RecordStatus.ACT.toString());
            em.merge(role);
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
            Role role;
            try {
                role = em.getReference(Role.class, id);
                role.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The role with id " + id + " no longer exists.", enfe);
            }
            em.remove(role);
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
            Query q = em.createQuery("select o from Role as o");
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
            Query q = em.createQuery("select o from Role as o where o.name like :name");
      
           return q.setParameter("name","%" + searchDTO.getName() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
