package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Servico;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * 🧴 DAO responsável pelos serviços do petshop (Banho, Tosa, Vacina, etc.)
 */
public class ServicoDAO extends JPADao<Servico> {

    public ServicoDAO(EntityManager em) {
        super(em, Servico.class);
    }

    @Override
    public void salvar(Servico servico) {
        em.getTransaction().begin();
        em.persist(servico);
        em.getTransaction().commit();
    }

    @Override
    public List<Servico> listarTodos() {
        return em.createQuery("FROM Servico", Servico.class).getResultList();
    }

    public Servico buscarPorId(Long id) {
        return em.find(Servico.class, id);
    }
}
