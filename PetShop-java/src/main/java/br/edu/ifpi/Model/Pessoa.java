package br.edu.ifpi.Model;

import jakarta.persistence.*;

/**
 * 🐾 Classe Abstrata Pessoa
 * Serve como base para Cliente e Funcionario.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100)
    protected String nome;
    
    @Column(name = "telefone", length = 20)
    protected String telefone;

    public Pessoa() {
        // Construtor padrão necessário para JPA
    }

    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}