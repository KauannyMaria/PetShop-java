package br.edu.ifpi.factory;

import br.edu.ifpi.Model.Servico;
import br.edu.ifpi.Model.Banho;
import br.edu.ifpi.Model.Tosa;
import br.edu.ifpi.Model.ServicoPadrao;

/**
 * 🏭 Factory Method para criação de Serviços
 * Padrões de Projeto: Factory Method + Singleton
 * 
 * Vantagens:
 * - Centraliza a criação de serviços
 * - Garante uma única instância da factory
 * - Facilita a manutenção e expansão
 * - Reduz duplicação de código
 */
public class ServicoFactory {
    
    private static ServicoFactory instance;
    
    // Construtor privado para evitar instanciação externa
    private ServicoFactory() {
    }
    
    // Método para obter a instância única (Singleton)
    public static synchronized ServicoFactory getInstance() {
        if (instance == null) {
            instance = new ServicoFactory();
        }
        return instance;
    }
    
    /**
     * Cria um serviço baseado no tipo informado
     * 
     * @param tipo tipo do serviço (banho, tosa, vacina, etc)
     * @param preco valor do serviço
     * @return instância de Servico
     */
    public Servico criarServico(String tipo, double preco) {
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
    public Servico criarServicoComPrecosPadrao(String tipo) {
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
    public String[] tiposEspecializados() {
        return new String[]{"banho", "tosa"};
    }
}
