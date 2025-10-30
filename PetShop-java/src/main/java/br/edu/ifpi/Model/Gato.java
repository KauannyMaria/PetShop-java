package br.edu.ifpi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gato")
public class Gato extends Animal {

    public Gato() {}

    public Gato(String nome, int idade, String raca) {
        super(nome, idade, raca);
    }

    @Override
    public void emitirSom() {
        System.out.println("Miau!");
    }

    @Override
    public String getEspecie() {
        return "Gato";
    }

    @Override
    public String toString() {
        return "Gato [nome=" + nome + ", raça=" + raca + ", idade=" + idade + "]";
    }
}
