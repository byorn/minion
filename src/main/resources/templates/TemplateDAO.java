/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package {PackageNameStart}.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import {PackageNameStart}.dao.exceptions.NonexistentEntityException;
import {PackageNameStart}.dtos.Search{Entity}DTO;
import {PackageNameStart}.entities.{Entity};
import {PackageNameStart}.util.RecordStatus;


/**
 *
 * @author 260514b
 */
public class {Entity}DAO implements Serializable {

    private static  final EntityManager em = null;
    
    private static {Entity}DAO instance = new {Entity}DAO();
    
    private {Entity}DAO(){};
    
    public static {Entity}DAO instance(){
        return instance;
    }

    public EntityManager getEntityManager() {
    
        if(em == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
            return factory.createEntityManager();
        }
        return em;
        
        
    }

    public void create({Entity} obj) {
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

    public void edit({Entity} obj) throws NonexistentEntityException, Exception {
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
            {Entity} obj;
            try {
                obj = em.getReference({Entity}.class, id);
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

    public List<{Entity}> find{Entity}Entities() {
        return find{Entity}Entities(true, -1, -1);
    }

    public List<{Entity}> find{Entity}Entities(int maxResults, int firstResult) {
        return find{Entity}Entities(false, maxResults, firstResult);
    }

    private List<{Entity}> find{Entity}Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from {Entity} as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public {Entity} find{Entity}(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find({Entity}.class, id);
        } finally {
            em.close();
        }
    }

    public int get{Entity}Count() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from {Entity} as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<{Entity}> find{Entity}Entities(Search{Entity}DTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from {Entity} as o where o.objname like :objname");
      
           return q.setParameter("objname","%" + searchDTO.get{Entity}name() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
