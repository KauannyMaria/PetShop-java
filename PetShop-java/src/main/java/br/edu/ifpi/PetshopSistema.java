package br.edu.ifpi;

import br.edu.ifpi.Model.*;
import br.edu.ifpi.util.JPAUtil;
import br.edu.ifpi.DAO.*;
import br.edu.ifpi.factory.AnimalFactory;
import br.edu.ifpi.factory.ServicoFactory;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class PetshopSistema {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDAO clienteDAO = new ClienteDAO(em);
        AnimalDAO animalDAO = new AnimalDAO(em);
        ServicoDAO servicoDAO = new ServicoDAO(em);
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(em);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(em);

        Scanner sc = new Scanner(System.in);
        int opcao;

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║     🐾  SISTEMA PET AMIGO  🐾            ║");
        System.out.println("╚════════════════════════════════════════════╝");

        do {
            System.out.println("\n┌────────────────────────────────────────────┐");
            System.out.println("│        🔐 SELECIONE SEU PERFIL             │");
            System.out.println("├────────────────────────────────────────────┤");
            System.out.println("│   1 - � Acesso CLIENTE                    │");
            System.out.println("│   2 - 💼 Acesso FUNCIONÁRIO                │");
            System.out.println("│   0 - 🚪 Sair do Sistema                   │");
            System.out.println("└────────────────────────────────────────────┘");
            System.out.print("➤ Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                
                // ========== ÁREA DO CLIENTE ==========
                case 1 -> {
                    int opcaoCliente;
                    do {
                        System.out.println("\n╔════════════════════════════════════════════╗");
                        System.out.println("║         👤 ÁREA DO CLIENTE                 ║");
                        System.out.println("╚════════════════════════════════════════════╝");
                        System.out.println("│   1 - 🐾 Listar Meus Animais               │");
                        System.out.println("│   2 - 🛁 Ver Serviços Disponíveis          │");
                        System.out.println("│   3 - 📅 Agendar Serviço                   │");
                        System.out.println("│   4 - 📋 Meus Agendamentos                 │");
                        System.out.println("│   0 - ⬅️  Voltar ao Menu Principal          │");
                        System.out.println("└────────────────────────────────────────────┘");
                        System.out.print("➤ Escolha uma opção: ");
                        opcaoCliente = sc.nextInt();
                        sc.nextLine();
                        
                        switch (opcaoCliente) {
                            case 1 -> { // Listar animais
                                System.out.println("\n┌─────────────────────────────────────┐");
                                System.out.println("│  🐾 MEUS ANIMAIS                    │");
                                System.out.println("└─────────────────────────────────────┘");
                                List<Animal> animais = animalDAO.listarTodos();
                                if (animais.isEmpty()) {
                                    System.out.println("Nenhum animal cadastrado.");
                                } else {
                                    for (Animal a : animais) {
                                        System.out.println("ID: " + a.getId() +
                                                " | Nome: " + a.getNome() +
                                                " | Espécie: " + a.getEspecie() +
                                                " | Dono: " + a.getDono().getNome());
                                    }
                                }
                            }
                            case 2 -> { // Ver serviços
                                System.out.println("\n┌─────────────────────────────────────┐");
                                System.out.println("│  🛁 SERVIÇOS DISPONÍVEIS            │");
                                System.out.println("└─────────────────────────────────────┘");
                                List<Servico> servicos = servicoDAO.listarTodos();
                                if (servicos.isEmpty()) {
                                    System.out.println("Nenhum serviço disponível.");
                                } else {
                                    for (Servico s : servicos) {
                                        System.out.println("ID: " + s.getId() +
                                                " | Tipo: " + s.getTipo() +
                                                " | Preço: R$ " + s.getPreco());
                                    }
                                }
                            }
                            case 3 -> { // Agendar serviço
                                System.out.println("\n┌─────────────────────────────────────┐");
                                System.out.println("│  � AGENDAR SERVIÇO                 │");
                                System.out.println("└─────────────────────────────────────┘");
                                System.out.print("ID do Cliente: ");
                                int idCliente = sc.nextInt();
                                System.out.print("ID do Animal: ");
                                int idAnimal = sc.nextInt();
                                System.out.print("ID do Serviço: ");
                                int idServico = sc.nextInt();
                                sc.nextLine();

                                Cliente cliente = clienteDAO.buscarPorId((long) idCliente);
                                Animal animal = animalDAO.buscarPorId((long) idAnimal);
                                Servico servico = servicoDAO.buscarPorId((long) idServico);

                                if (cliente == null || animal == null || servico == null) {
                                    System.out.println("❌ Dados inválidos. Verifique os IDs informados.");
                                } else {
                                    Agendamento agendamento = new Agendamento();
                                    agendamento.setCliente(cliente);
                                    agendamento.setAnimal(animal);
                                    agendamento.setServico(servico);
                                    agendamentoDAO.salvar(agendamento);
                                    System.out.println("✅ Agendamento realizado com sucesso!");
                                }
                            }
                            case 4 -> { // Ver agendamentos
                                System.out.println("\n┌─────────────────────────────────────┐");
                                System.out.println("│  📋 MEUS AGENDAMENTOS               │");
                                System.out.println("└─────────────────────────────────────┘");
                                List<Agendamento> agendamentos = agendamentoDAO.listarTodos();
                                if (agendamentos.isEmpty()) {
                                    System.out.println("Nenhum agendamento encontrado.");
                                } else {
                                    for (Agendamento a : agendamentos) {
                                        System.out.println("ID: " + a.getId() +
                                                " | Cliente: " + a.getCliente().getNome() +
                                                " | Animal: " + a.getAnimal().getNome() +
                                                " | Serviço: " + a.getServico().getTipo());
                                    }
                                }
                            }
                            case 0 -> System.out.println("↩️ Voltando ao menu principal...");
                            default -> System.out.println("❌ Opção inválida!");
                        }
                    } while (opcaoCliente != 0);
                }
                
                // ========== ÁREA DO FUNCIONÁRIO ==========
                case 2 -> {
                    int opcaoFunc;
                    do {
                        System.out.println("\n╔════════════════════════════════════════════╗");
                        System.out.println("║       💼 ÁREA DO FUNCIONÁRIO               ║");
                        System.out.println("╚════════════════════════════════════════════╝");
                        System.out.println("│  👤 CLIENTES                               │");
                        System.out.println("│   1 - Cadastrar Cliente                    │");
                        System.out.println("│   2 - Listar Clientes                      │");
                        System.out.println("├────────────────────────────────────────────┤");
                        System.out.println("│  🐾 ANIMAIS                                │");
                        System.out.println("│   3 - Cadastrar Animal                     │");
                        System.out.println("│   4 - Listar Animais                       │");
                        System.out.println("├────────────────────────────────────────────┤");
                        System.out.println("│  💼 FUNCIONÁRIOS                           │");
                        System.out.println("│   5 - Cadastrar Funcionário                │");
                        System.out.println("│   6 - Listar Funcionários                  │");
                        System.out.println("├────────────────────────────────────────────┤");
                        System.out.println("│  🛁 SERVIÇOS                               │");
                        System.out.println("│   7 - Cadastrar Serviço                    │");
                        System.out.println("│   8 - Listar Serviços                      │");
                        System.out.println("├────────────────────────────────────────────┤");
                        System.out.println("│  📅 AGENDAMENTOS                           │");
                        System.out.println("│   9 - Criar Agendamento                    │");
                        System.out.println("│  10 - Listar Agendamentos                  │");
                        System.out.println("├────────────────────────────────────────────┤");
                        System.out.println("│   0 - ⬅️  Voltar ao Menu Principal          │");
                        System.out.println("└────────────────────────────────────────────┘");
                        System.out.print("➤ Escolha uma opção: ");
                        opcaoFunc = sc.nextInt();
                        sc.nextLine();
                        
                        switch (opcaoFunc) {

                            // Cadastro de Cliente
                            case 1 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  👤 CADASTRO DE CLIENTE             │");
                    System.out.println("└─────────────────────────────────────┘");
                    String nome;
                    do {
                        System.out.print("Nome: ");
                        nome = sc.nextLine().trim();
                        if (nome.isEmpty()) System.out.println("❌ Nome não pode ser vazio!");
                    } while (nome.isEmpty());

                    String telefone;
                    do {
                        System.out.print("Telefone: ");
                        telefone = sc.nextLine().trim();
                        if (telefone.isEmpty()) System.out.println("❌ Telefone não pode ser vazio!");
                    } while (telefone.isEmpty());

                    String endereco;
                    do {
                        System.out.print("Endereço: ");
                        endereco = sc.nextLine().trim();
                        if (endereco.isEmpty()) System.out.println("❌ Endereço não pode ser vazio!");
                    } while (endereco.isEmpty());

                    Cliente cliente = new Cliente(nome, telefone, endereco);
                    clienteDAO.salvar(cliente);
                    System.out.println("✅ Cliente cadastrado com sucesso!");
                }

                            // Listagem de Clientes
                            case 2 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  📋 LISTA DE CLIENTES               │");
                    System.out.println("└─────────────────────────────────────┘");
                    List<Cliente> clientes = clienteDAO.listarTodos();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                }

                            // Cadastro de Animal
                            case 3 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  🐾 CADASTRO DE ANIMAL              │");
                    System.out.println("└─────────────────────────────────────┘");
                    String nome;
                    do {
                        System.out.print("Nome: ");
                        nome = sc.nextLine().trim();
                        if (nome.isEmpty()) System.out.println("❌ Nome não pode ser vazio!");
                    } while (nome.isEmpty());

                    String especie;
                    do {
                        System.out.print("Espécie (ex: cachorro, gato...): ");
                        especie = sc.nextLine().trim().toLowerCase();
                        if (especie.isEmpty()) System.out.println("❌ Espécie não pode ser vazia!");
                    } while (especie.isEmpty());

                    String raca;
                    do {
                        System.out.print("Raça: ");
                        raca = sc.nextLine().trim();
                        if (raca.isEmpty()) System.out.println("❌ Raça não pode ser vazia!");
                    } while (raca.isEmpty());

                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine(); // limpar buffer

                    System.out.print("ID do Dono: ");
                    int idDono = sc.nextInt();
                    sc.nextLine();

                    Cliente dono = clienteDAO.buscarPorId((long) idDono);
                    if (dono == null) {
                        System.out.println("❌ Cliente não encontrado.");
                    } else {
                        try {
                            // Usando Factory Method para criar o animal
                            Animal animal = AnimalFactory.criarAnimal(especie, nome, idade, raca);
                            animal.setDono(dono);
                            animalDAO.salvar(animal);
                            System.out.println("✅ Animal cadastrado com sucesso!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }
                }

                            // Listagem de Animais
                            case 4 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  📋 LISTA DE ANIMAIS                │");
                    System.out.println("└─────────────────────────────────────┘");
                    List<Animal> animais = animalDAO.listarTodos();
                    if (animais.isEmpty()) {
                        System.out.println("Nenhum animal cadastrado.");
                    } else {
                        for (Animal a : animais) {
                            System.out.println("ID: " + a.getId() +
                                    " | Nome: " + a.getNome() +
                                    " | Espécie: " + a.getEspecie() +
                                    " | Dono: " + a.getDono().getNome());
                        }
                    }
                }

                            // Cadastro de Funcionário
                            case 5 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  💼 CADASTRO DE FUNCIONÁRIO         │");
                    System.out.println("└─────────────────────────────────────┘");
                    String nome;
                    do {
                        System.out.print("Nome: ");
                        nome = sc.nextLine().trim();
                        if (nome.isEmpty()) System.out.println("❌ Nome não pode ser vazio!");
                    } while (nome.isEmpty());

                    String cargo;
                    do {
                        System.out.print("Cargo: ");
                        cargo = sc.nextLine().trim();
                        if (cargo.isEmpty()) System.out.println("❌ Cargo não pode ser vazio!");
                    } while (cargo.isEmpty());

                    double salario;
                    do {
                        System.out.print("Salário: ");
                        salario = sc.nextDouble();
                        sc.nextLine();
                        if (salario <= 0) System.out.println("❌ Salário deve ser maior que zero!");
                    } while (salario <= 0);

                    Funcionario funcionario = new Funcionario(nome, cargo, salario);
                    funcionarioDAO.salvar(funcionario);
                    System.out.println("✅ Funcionário cadastrado com sucesso!");
                }

                            // Listagem de Funcionários
                            case 6 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  📋 LISTA DE FUNCIONÁRIOS           │");
                    System.out.println("└─────────────────────────────────────┘");
                    List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Funcionario f : funcionarios) {
                            System.out.println("ID: " + f.getId() +
                                    " | Nome: " + f.getNome() +
                                    " | Cargo: " + f.getCargo() +
                                    " | Salário: R$ " + f.getSalario());
                        }
                    }
                }

                            // Cadastro de Serviço
                            case 7 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  🛁 CADASTRO DE SERVIÇO             │");
                    System.out.println("└─────────────────────────────────────┘");
                    String tipo;
                    do {
                        System.out.print("Tipo (Ex: Banho, Tosa, Vacina...): ");
                        tipo = sc.nextLine().trim();
                        if (tipo.isEmpty()) System.out.println("❌ Tipo não pode ser vazio!");
                    } while (tipo.isEmpty());

                    double preco;
                    do {
                        System.out.print("Preço: ");
                        preco = sc.nextDouble();
                        sc.nextLine();
                        if (preco <= 0) System.out.println("❌ O preço deve ser maior que zero!");
                    } while (preco <= 0);

                    try {
                        // Usando Factory Method para criar o serviço
                        Servico servico = ServicoFactory.criarServico(tipo, preco);
                        servicoDAO.salvar(servico);
                        System.out.println("✅ Serviço cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }

                            // Listagem de Serviços
                            case 8 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  📋 LISTA DE SERVIÇOS               │");
                    System.out.println("└─────────────────────────────────────┘");
                    List<Servico> servicos = servicoDAO.listarTodos();
                    if (servicos.isEmpty()) {
                        System.out.println("Nenhum serviço cadastrado.");
                    } else {
                        for (Servico s : servicos) {
                            System.out.println("ID: " + s.getId() +
                                    " | Tipo: " + s.getTipo() +
                                    " | Preço: R$ " + s.getPreco());
                        }
                    }
                }

                            // Agendar Serviço
                            case 9 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  📅 AGENDAMENTO DE SERVIÇO          │");
                    System.out.println("└─────────────────────────────────────┘");
                    System.out.print("ID do Cliente: ");
                    int idCliente = sc.nextInt();
                    System.out.print("ID do Animal: ");
                    int idAnimal = sc.nextInt();
                    System.out.print("ID do Serviço: ");
                    int idServico = sc.nextInt();
                    sc.nextLine();

                    Cliente cliente = clienteDAO.buscarPorId((long) idCliente);
                    Animal animal = animalDAO.buscarPorId((long) idAnimal);
                    Servico servico = servicoDAO.buscarPorId((long) idServico);

                    if (cliente == null || animal == null || servico == null) {
                        System.out.println("❌ Dados inválidos. Verifique os IDs informados.");
                    } else {
                        Agendamento agendamento = new Agendamento();
                        agendamento.setCliente(cliente);
                        agendamento.setAnimal(animal);
                        agendamento.setServico(servico);
                        agendamentoDAO.salvar(agendamento);
                        System.out.println("✅ Agendamento realizado com sucesso!");
                    }
                }

                            // Listagem de Agendamentos
                            case 10 -> {
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│  📋 LISTA DE AGENDAMENTOS           │");
                    System.out.println("└─────────────────────────────────────┘");
                    List<Agendamento> agendamentos = agendamentoDAO.listarTodos();
                    if (agendamentos.isEmpty()) {
                        System.out.println("Nenhum agendamento encontrado.");
                    } else {
                        for (Agendamento a : agendamentos) {
                            System.out.println("ID: " + a.getId() +
                                    " | Cliente: " + a.getCliente().getNome() +
                                    " | Animal: " + a.getAnimal().getNome() +
                                    " | Serviço: " + a.getServico().getTipo());
                        }
                    }
                }
                            
                            case 0 -> System.out.println("↩️ Voltando ao menu principal...");
                            default -> System.out.println("❌ Opção inválida!");
                        }
                    } while (opcaoFunc != 0);
                }

                case 0 -> {
                    System.out.println("\n╔════════════════════════════════════════════╗");
                    System.out.println("║  Encerrando o sistema... Até logo! 🐾     ║");
                    System.out.println("╚════════════════════════════════════════════╝");
                }

                default -> System.out.println("\n❌ Opção inválida! Escolha 1 (Cliente), 2 (Funcionário) ou 0 (Sair).");
            }

        } while (opcao != 0);

        em.close();
        JPAUtil.fechar();
        sc.close();
    }
}
