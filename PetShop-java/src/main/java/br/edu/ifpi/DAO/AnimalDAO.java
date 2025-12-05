package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Animal;
import jakarta.persistence.EntityManager;

import java.util.List;

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

    // 🔍 Buscar animais pelo dono
    public List<Animal> buscarPorDono(Long donoId) {
        return em.createQuery(
                "SELECT a FROM Animal a WHERE a.dono.id = :donoId",
                Animal.class
        )
        .setParameter("donoId", donoId)
        .getResultList();
    }
}
