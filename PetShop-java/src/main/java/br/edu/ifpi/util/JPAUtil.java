package br.edu.ifpi.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para gerenciar o EntityManager.
 */
public class JPAUtil {

    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("petshopPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void fechar() {
        emf.close();
    }
}
