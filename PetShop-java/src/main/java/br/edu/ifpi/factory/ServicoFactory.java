package br.edu.ifpi.factory;

import br.edu.ifpi.Model.Servico;
import br.edu.ifpi.Model.Banho;
import br.edu.ifpi.Model.Tosa;
import br.edu.ifpi.Model.ServicoPadrao;

/**
 * 🏭 Factory Method para criação de Serviços
 * Padrão de Projeto: Factory Method
 * 
 * Vantagens:
 * - Centraliza a criação de serviços
 * - Facilita a manutenção e expansão
 * - Reduz duplicação de código
 */
public class ServicoFactory {
    
    /**
     * Cria um serviço baseado no tipo informado
     * 
     * @param tipo tipo do serviço (banho, tosa, vacina, etc)
     * @param preco valor do serviço
     * @return instância de Servico
     */
    public static Servico criarServico(String tipo, double preco) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de serviço não pode ser vazio");
        }
        
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        
        Servico servico;
        
        switch (tipo.toLowerCase().trim()) {
            case "banho", "bath", "higiene" -> servico = new Banho();
            case "tosa", "grooming", "corte", "tosquia" -> servico = new Tosa();
            default -> servico = new ServicoPadrao();
        }
        
        servico.setTipo(tipo);
        servico.setPreco(preco);
        
        return servico;
    }
    
    /**
     * Cria um serviço com preço padrão baseado no tipo
     * 
     * @param tipo tipo do serviço
     * @return instância de Servico com preço padrão
     */
    public static Servico criarServicoComPrecosPadrao(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de serviço não pode ser vazio");
        }
        
        double precoPadrao = switch (tipo.toLowerCase().trim()) {
            case "banho", "bath", "higiene" -> 50.0;
            case "tosa", "grooming", "corte", "tosquia" -> 80.0;
            case "vacina", "vaccine" -> 100.0;
            case "consulta", "checkup" -> 120.0;
            default -> 60.0;
        };
        
        return criarServico(tipo, precoPadrao);
    }
    
    /**
     * Lista os tipos de serviços especializados disponíveis
     * 
     * @return array com os tipos de serviços especializados
     */
    public static String[] tiposEspecializados() {
        return new String[]{"banho", "tosa"};
    }
}
