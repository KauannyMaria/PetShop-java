package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Agendamento;
import jakarta.persistence.EntityManager;
import java.util.List;

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

    // 🔍 Buscar só os agendamentos do cliente
    public List<Agendamento> buscarPorCliente(Long clienteId) {
        return em.createQuery(
                "SELECT a FROM Agendamento a WHERE a.cliente.id = :clienteId",
                Agendamento.class
        )
        .setParameter("clienteId", clienteId)
        .getResultList();
    }
}
