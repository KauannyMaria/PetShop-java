package br.edu.ifpi.Model;

import jakarta.persistence.Entity;

@Entity
public class Banho extends Servico {

    public Banho() {}

    @Override
    public void executarServico(Animal animal) {
        System.out.println("🐶 Dando banho em " + animal.getNome());
    }
}
