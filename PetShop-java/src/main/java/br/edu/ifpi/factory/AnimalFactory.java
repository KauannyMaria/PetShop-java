package br.edu.ifpi.factory;

import br.edu.ifpi.Model.Animal;
import br.edu.ifpi.Model.Cachorro;
import br.edu.ifpi.Model.Gato;

/**
 * 🏭 Factory Method para criação de Animais
 * Padrão de Projeto: Factory Method
 * 
 * Vantagens:
 * - Encapsula a lógica de criação de objetos
 * - Facilita a adição de novos tipos de animais
 * - Reduz acoplamento no código cliente
 */
public class AnimalFactory {
    
    /**
     * Cria um animal baseado na espécie informada
     * 
     * @param especie tipo do animal (cachorro, gato, etc)
     * @param nome nome do animal
     * @param idade idade do animal
     * @param raca raça do animal
     * @return instância de Animal (Cachorro ou Gato)
     * @throws IllegalArgumentException se a espécie não for suportada
     */
    public static Animal criarAnimal(String especie, String nome, int idade, String raca) {
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie não pode ser vazia");
        }
        
        return switch (especie.toLowerCase().trim()) {
            case "cachorro", "dog", "cao" -> new Cachorro(nome, idade, raca);
            case "gato", "cat", "felino" -> new Gato(nome, idade, raca);
            default -> throw new IllegalArgumentException(
                "Espécie não suportada: " + especie + 
                ". Opções válidas: cachorro, gato"
            );
        };
    }
    
    /**
     * Verifica se uma espécie é suportada pela factory
     * 
     * @param especie tipo do animal
     * @return true se a espécie é suportada, false caso contrário
     */
    public static boolean especieSuportada(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            return false;
        }
        
        String especieLower = especie.toLowerCase().trim();
        return especieLower.equals("cachorro") || 
               especieLower.equals("dog") || 
               especieLower.equals("cao") ||
               especieLower.equals("gato") || 
               especieLower.equals("cat") || 
               especieLower.equals("felino");
    }
}
