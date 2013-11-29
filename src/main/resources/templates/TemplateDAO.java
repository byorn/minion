/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package {PackageNameStart}.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import {PackageNameStart}.dao.exceptions.NonexistentEntityException;
import {PackageNameStart}.dtos.Search{Entity}DTO;
import {PackageNameStart}.entities.{Entity};
import {PackageNameStart}.util.RecordStatus;
import static softwareperson.framework.util.FrameworkUtil.*;


/**
 *
 * @author 260514b
 */
public class {Entity}DAO implements Serializable {

  
    
    private static {Entity}DAO instance = new {Entity}DAO();
    
    private {Entity}DAO(){};
    
    public static {Entity}DAO instance(){
        return instance;
    }

  

    public void create({Entity} {entity}) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            {entity}.setStatus(RecordStatus.ACT.toString());
            em.persist({entity});
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit({Entity} {entity}) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
           
            em.getTransaction().begin();
            {entity}.setStatus(RecordStatus.ACT.toString());
            em.merge({entity});
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
            {Entity} {entity};
            try {
                {entity} = em.getReference({Entity}.class, id);
                {entity}.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The {entity} with id " + id + " no longer exists.", enfe);
            }
            em.remove({entity});
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
            Query q = em.createQuery("select o from {Entity} as o");
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
            Query q = em.createQuery("select o from {Entity} as o where o.name like :name");
      
           return q.setParameter("name","%" + searchDTO.getName() + "%").getResultList();
        } finally {
            em.close();
        }
    }
    
}
