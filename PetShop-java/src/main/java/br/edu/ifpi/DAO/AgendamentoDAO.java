package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Agendamento;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * 📅 DAO responsável pelos agendamentos do sistema.
 */
public class AgendamentoDAO extends JPADao<Agendamento> {

    public AgendamentoDAO(EntityManager em) {
        super(em, Agendamento.class);
    }

    @Override
    public void salvar(Agendamento agendamento) {
        em.getTransaction().begin();
        em.persist(agendamento);
        em.getTransaction().commit();
    }

    @Override
    public List<Agendamento> listarTodos() {
        return em.createQuery("FROM Agendamento", Agendamento.class).getResultList();
    }

    public Agendamento buscarPorId(Long id) {
        return em.find(Agendamento.class, id);
    }
}
