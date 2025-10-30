package br.edu.ifpi.Model;

import jakarta.persistence.Entity;

@Entity
public class ServicoPadrao extends Servico {

    public ServicoPadrao() {}

    @Override
    public void executarServico(Animal animal) {
        System.out.println("💼 Executando serviço genérico em " + animal.getNome());
    }
}
