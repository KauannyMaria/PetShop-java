package br.edu.ifpi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cachorro")
public class Cachorro extends Animal {

    public Cachorro() {}

    public Cachorro(String nome, int idade, String raca) {
        super(nome, idade, raca);
    }

    @Override
    public void emitirSom() {
        System.out.println("Au au!");
    }

    @Override
    public String getEspecie() {
        return "Cachorro";
    }

    @Override
    public String toString() {
        return "Cachorro [nome=" + nome + ", raça=" + raca + ", idade=" + idade + "]";
    }
}
