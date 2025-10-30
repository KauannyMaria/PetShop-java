package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Animal;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * 🐕 DAO responsável por gerenciar os dados dos animais no banco.
 */
public class AnimalDAO extends JPADao<Animal> {

    public AnimalDAO(EntityManager em) {
        super(em, Animal.class);
    }

    // Salvar animal
    @Override
    public void salvar(Animal animal) {
        em.getTransaction().begin();
        em.persist(animal);
        em.getTransaction().commit();
    }

    // Listar todos os animais
    @Override
    public List<Animal> listarTodos() {
        return em.createQuery("FROM Animal", Animal.class).getResultList();
    }

    // Buscar animal por ID
    public Animal buscarPorId(Long id) {
        return em.find(Animal.class, id);
    }
}
