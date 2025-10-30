package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import br.edu.ifpi.Model.Banho;

public class BanhoDAO extends JPADao<Banho> {
    public BanhoDAO(EntityManager em) {
        super(em, Banho.class);
    }
}
