package br.edu.ifpi.Model;

import jakarta.persistence.Entity;

@Entity
public class Tosa extends Servico {

    public Tosa() {}

    @Override
    public void executarServico(Animal animal) {
        System.out.println("✂️ Tosando " + animal.getNome());
    }
}
