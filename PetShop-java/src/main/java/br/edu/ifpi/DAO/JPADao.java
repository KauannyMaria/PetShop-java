package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * 🧩 Classe genérica base para todos os DAOs (reutilizável)
 */
public abstract class JPADao<T> {

    protected EntityManager em;
    private Class<T> classe;

    public JPADao(EntityManager em, Class<T> classe) {
        this.em = em;
        this.classe = classe;
    }

    public void salvar(T entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }

    public List<T> listarTodos() {
        return em.createQuery("FROM " + classe.getSimpleName(), classe).getResultList();
    }

    public T buscarPorId(Long idDono) {
    return em.find(classe, idDono);
}

    public void remover(Long id) {
        T entidade = buscarPorId(id);
        if (entidade != null) {
            em.getTransaction().begin();
            em.remove(entidade);
            em.getTransaction().commit();
        }
    }

}
