package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import br.edu.ifpi.Model.Cliente;

public class ClienteDAO extends JPADao<Cliente> {
    public ClienteDAO(EntityManager em) {
        super(em, Cliente.class);
    }
}
