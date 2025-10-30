package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Funcionario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FuncionarioDAO {
    private EntityManager em;

    public FuncionarioDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public List<Funcionario> listarTodos() {
        return em.createQuery("FROM Funcionario", Funcionario.class).getResultList();
    }

    public Funcionario buscarPorId(Long id) {
        return em.find(Funcionario.class, id);
    }
}
