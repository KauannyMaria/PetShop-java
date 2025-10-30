# 🐾 PetA - Sistema de Agendamento para Petshop# PetA - Sistema de Agendamento para Petshop



Sistema de agendamento para petshop desenvolvido em Java com JPA e PostgreSQL, utilizando padrão de projeto Factory Method.Sistema de agendamento para petshop desenvolvido em Java com JPA e MySQL.



## 📋 Funcionalidades## Funcionalidades



### Acesso Cliente:- Cadastro de clientes e funcionários

- 👀 Visualizar animais cadastrados- Cadastro de animais (cachorros e gatos)

- 💼 Visualizar serviços disponíveis- Agendamento de serviços (banho e tosa)

- 📅 Criar agendamentos- Emissão de recibos de agendamento

- 📋 Consultar agendamentos próprios

## Tecnologias Utilizadas

### Acesso Funcionário:

- 👥 Cadastro de clientes e funcionários- Java 11

- 🐶 Cadastro de animais (cachorros e gatos)- JPA (Jakarta Persistence)

- ✂️ Cadastro de serviços (banho, tosa e personalizados)- Hibernate

- 📅 Gerenciamento completo de agendamentos- MySQL

- 🗑️ Remoção de registros (CRUD completo)- Maven



## 🛠️ Tecnologias Utilizadas## Estrutura do Banco de Dados



- **Java 17** - Linguagem de programaçãoO projeto utiliza mapeamento ORM com as seguintes características:

- **JPA (Jakarta Persistence API 3.1)** - Especificação para persistência- Herança com estratégia JOINED

- **Hibernate 6.4.4** - Implementação JPA/ORM- Relacionamentos One-to-Many e Many-to-One

- **PostgreSQL 42.7.3** - Banco de dados relacional- Tabelas: pessoa, cliente, funcionario, animal, cachorro, gato, servico_banho, servico_tosa, agendamento

- **Maven 3.9.11** - Gerenciador de dependências e build

- **Render Cloud** - Hospedagem do banco de dados PostgreSQL## Configuração



## 🏗️ Padrão de Projeto: Factory Method1. Instalar e configurar MySQL

2. Criar banco de dados `petshop_db`

O sistema implementa o **Factory Method** para criação de objetos das hierarquias `Animal` e `Servico`:3. Configurar usuário e senha no arquivo `persistence.xml`

4. Executar o Maven para baixar as dependências

### AnimalFactory5. Executar a classe `PetshopSistema`

Responsável por criar instâncias de animais de forma centralizada:

- **Entrada**: espécie, nome, idade, raça## Como Executar

- **Saída**: Objeto `Cachorro` ou `Gato`

- **Vantagem**: Desacoplamento - o código cliente não precisa conhecer as classes concretas```bash

mvn compile

```javamvn exec:java -Dexec.mainClass="pessoa.PetshopSistema"
Animal animal = AnimalFactory.criarAnimal("cachorro", "Rex", 3, "Labrador");
```

### ServicoFactory
Responsável por criar instâncias de serviços:
- **Entrada**: tipo de serviço, preço
- **Saída**: Objeto `Banho`, `Tosa` ou `ServicoPadrao`
- **Vantagem**: Centraliza a lógica de criação e permite preços padrão

```java
Servico banho = ServicoFactory.criarServicoComPrecosPadrao("banho");
```

**Benefícios do Factory Method no projeto:**
- ✅ Reduz acoplamento entre classes
- ✅ Facilita manutenção e extensão
- ✅ Centraliza validação de tipos/espécies
- ✅ Permite criação de objetos sem expor lógica de instanciação

## 🗄️ Estrutura do Banco de Dados

O projeto utiliza mapeamento ORM com JPA/Hibernate seguindo estas estratégias:

### Herança com Estratégia JOINED
Três hierarquias de classes utilizam herança:

1. **Pessoa** (tabela base)
   - `Cliente` (tabela: cliente) - herda de Pessoa
   - `Funcionario` (tabela: funcionario) - herda de Pessoa

2. **Animal** (tabela base)
   - `Cachorro` (tabela: cachorro) - herda de Animal
   - `Gato` (tabela: gato) - herda de Animal

3. **Servico** (tabela base)
   - `Banho` (tabela: banho) - herda de Servico
   - `Tosa` (tabela: tosa) - herda de Servico
   - `ServicoPadrao` (tabela: servico_padrao) - herda de Servico

### Relacionamentos JPA

#### **@OneToMany (Um-para-Muitos)**
- `Cliente` → `Agendamento`: Um cliente pode ter vários agendamentos
- `Animal` → `Agendamento`: Um animal pode ter vários agendamentos
- `Servico` → `Agendamento`: Um serviço pode estar em vários agendamentos

#### **@ManyToOne (Muitos-para-Um)**
- `Agendamento` → `Cliente`: Vários agendamentos pertencem a um cliente
- `Agendamento` → `Animal`: Vários agendamentos pertencem a um animal
- `Agendamento` → `Servico`: Vários agendamentos utilizam um serviço

#### **@OneToOne (Um-para-Um)**
- `Cliente` → `Pessoa`: Cada cliente é uma pessoa (herança)
- `Funcionario` → `Pessoa`: Cada funcionário é uma pessoa (herança)
- `Cachorro` → `Animal`: Cada cachorro é um animal (herança)
- `Gato` → `Animal`: Cada gato é um animal (herança)

**Cascade Types utilizados:**
- `CascadeType.ALL` - Propaga todas as operações (persist, merge, remove, refresh)
- `CascadeType.PERSIST` - Salva entidades relacionadas automaticamente

## ⚙️ Configuração

### Pré-requisitos
- Java 17 ou superior
- Maven 3.9.x
- Conexão com internet (para acessar banco no Render)

### Banco de Dados
O sistema está configurado para usar PostgreSQL hospedado no **Render Cloud**:
- Host: `dpg-d41rm6euk2gs738oscgg-a.oregon-postgres.render.com`
- Database: `petshop_of4y`
- SSL: Habilitado (sslmode=require)

As credenciais estão configuradas no arquivo `src/main/resources/META-INF/persistence.xml`.

### Passos para Executar

1. **Clonar o repositório**
```bash
git clone <url-do-repositorio>
cd PetA
```

2. **Compilar o projeto**
```bash
mvn clean compile
```

3. **Executar o sistema**
```bash
mvn exec:java -q
```

O parâmetro `-q` (quiet mode) reduz as logs no console para melhor visualização do menu.

## 🚀 Como Usar

1. Ao iniciar, escolha o perfil de acesso:
   - **Opção 1**: Acesso Cliente (funcionalidades limitadas)
   - **Opção 2**: Acesso Funcionário (funcionalidades completas)

2. Navegue pelos menus específicos de cada perfil

3. O sistema gerencia automaticamente:
   - Conexão com banco de dados
   - Transações JPA
   - Relacionamentos entre entidades
   - Validações de dados

## 📂 Estrutura do Projeto

```
src/main/java/br/edu/ifpi/
├── PetshopSistema.java          # Classe principal com menu
├── DAO/                          # Data Access Objects
│   ├── AgendamentoDAO.java
│   ├── AnimalDAO.java
│   ├── ClienteDAO.java
│   ├── FuncionarioDAO.java
│   ├── ServicoDAO.java
│   └── JPADao.java
├── factory/                      # Factory Method Pattern
│   ├── AnimalFactory.java       # Cria Cachorro/Gato
│   └── ServicoFactory.java      # Cria Banho/Tosa/ServicoPadrao
├── Model/                        # Entidades JPA
│   ├── Pessoa.java              # Classe base (abstract)
│   ├── Cliente.java
│   ├── Funcionario.java
│   ├── Animal.java              # Classe base (abstract)
│   ├── Cachorro.java
│   ├── Gato.java
│   ├── Servico.java             # Classe base (abstract)
│   ├── Banho.java
│   ├── Tosa.java
│   ├── ServicoPadrao.java
│   └── Agendamento.java
└── util/
    └── JPAUtil.java             # Gerenciador EntityManager
```

## 📊 Diagrama de Relacionamentos

```
Cliente (1) -----> (*) Agendamento
Animal (1)  -----> (*) Agendamento
Servico (1) -----> (*) Agendamento

Pessoa (1) -----> (1) Cliente
Pessoa (1) -----> (1) Funcionario

Animal (1) -----> (1) Cachorro
Animal (1) -----> (1) Gato

Servico (1) ----> (1) Banho
Servico (1) ----> (1) Tosa
Servico (1) ----> (1) ServicoPadrao
```

## 👥 Autores

Projeto desenvolvido como parte do curso de desenvolvimento de software no IFPI.

---

**Versão:** 1.0  
**Última atualização:** Outubro 2025
