package br.edu.ifpi.Model;

import jakarta.persistence.*;

/**
 * 🐕 Classe Abstrata Animal
 * Serve como base para os animais de estimação.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "animal")
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100)
    protected String nome;
    
    @Column(name = "idade")
    protected int idade;
    
    @Column(name = "raca", length = 50)
    protected String raca;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Cliente dono;

    public Animal() {}

    public Animal(String nome, int idade, String raca) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
    }

    public abstract void emitirSom();
    public abstract String getEspecie();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public Cliente getDono() { return dono; }
    public void setDono(Cliente dono) { this.dono = dono; }

    @Override
    public String toString() {
        return getEspecie() + " {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", raca='" + raca + '\'' +
                ", dono='" + (dono != null ? dono.getNome() : "Sem dono") + '\'' +
                '}';
    }
}
