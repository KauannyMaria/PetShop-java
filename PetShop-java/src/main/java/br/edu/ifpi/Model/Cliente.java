package br.edu.ifpi.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 👤 Classe Cliente
 * Herda de Pessoa e tem uma lista de animais.
 */
@Entity
@Table(name = "cliente")
@DiscriminatorValue("CLIENTE")
public class Cliente extends Pessoa {
    @Column(name = "endereco", length = 200)
    private String endereco;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animais;

    public Cliente() {
        this.animais = new ArrayList<>();
    }

    public Cliente(String nome, String telefone, String endereco) {
        super(nome, telefone);
        this.endereco = endereco;
        this.animais = new ArrayList<>();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public void adicionarAnimal(Animal animal) {
        animal.setDono(this);
        this.animais.add(animal);
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", endereco='" + endereco + '\'' +
                ", animais=" + animais.size() +
                '}';
    }
}
