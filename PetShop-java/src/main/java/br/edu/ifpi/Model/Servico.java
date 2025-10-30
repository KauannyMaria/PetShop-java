package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private double preco;

    public Servico() {}

    public Servico(String tipo, double preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public abstract void executarServico(Animal animal);

    @Override
    public String toString() {
        return "ID: " + id + " | Tipo: " + tipo + " | Preço: R$" + preco;
    }
}
