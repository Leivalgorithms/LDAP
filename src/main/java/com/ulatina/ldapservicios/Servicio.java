package com.ulatina.ldapservicios;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class Servicio {

	private static EntityManagerFactory emf = null;
    private EntityManager em = null;
    
  //ThreadLocal para garantizar un EntityManager por hilo
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
    
    public Servicio(EntityManager em) {
        threadLocal.set(em);
    }
    
 // Inicializa EntityManagerFactory
    public static synchronized void initEntityManagerFactory(String persistenceUnit) {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory(persistenceUnit);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error al iniciar EntityManagerFactory", e);
            }
        }
    }
    
 // Obtiene el EntityManager actual para el hilo actual o crea uno nuevo
    public static EntityManager getEntityManager() {
        EntityManager entityManager = threadLocal.get();
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
            threadLocal.set(entityManager);
        }
        return entityManager;
    }

    public static void closeEntityManager() {
        EntityManager entityManager = threadLocal.get();
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        threadLocal.remove();
    }

    
    public static synchronized void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    public EntityManager getEm() {
        return getEntityManager();
    }

}
