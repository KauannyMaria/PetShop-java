package br.edu.ifpi.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para gerenciar o EntityManager.
 * Padrão de Projeto: Singleton
 * 
 * Vantagens:
 * - Garante uma única instância de EntityManagerFactory
 * - Economiza recursos do sistema
 * - Controla acesso ao gerenciador de entidades
 */
public class JPAUtil {

    private static JPAUtil instance;
    private final EntityManagerFactory emf;

    // Construtor privado para evitar instanciação externa
    private JPAUtil() {
        this.emf = Persistence.createEntityManagerFactory("petshopPU");
    }

    // Método para obter a instância única (Singleton)
    public static synchronized JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void fechar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
