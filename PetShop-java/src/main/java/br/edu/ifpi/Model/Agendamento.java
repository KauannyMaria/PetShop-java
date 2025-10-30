package br.edu.ifpi.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 🗓️ Classe Agendamento
 * Conecta Cliente, Animal e Serviço.
 */
@Entity
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_agendamento")
    private Date data;

    public Agendamento() {
        this.data = new Date(); // Data/hora atual por padrão
    }

    public Agendamento(Cliente cliente, Animal animal, Servico servico) {
        this.cliente = cliente;
        this.animal = animal;
        this.servico = servico;
        this.data = new Date();
    }

    public Agendamento(Cliente cliente, Animal animal, Servico servico, Date data) {
        this.cliente = cliente;
        this.animal = animal;
        this.servico = servico;
        this.data = data;
    }

    public void imprimirRecibo() {
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
        System.out.println("\n===== RECIBO DE AGENDAMENTO =====");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Animal: " + animal.getNome() + " (" + animal.getRaca() + ")");
        
        if (servico != null) {
            System.out.println("Serviço: " + servico.getTipo());
            System.out.printf("Valor: R$ %.2f\n", servico.getPreco());
        }
        
        System.out.println("Data: " + formatadorData.format(this.data));
        System.out.println("===============================");
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
    
    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
    
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    @Override
    public String toString() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String servicoNome = (servico != null) ? servico.getTipo() : "Nenhum";
        return "Agendamento {" +
                "id=" + id +
                ", cliente='" + (cliente != null ? cliente.getNome() : "N/A") + '\'' +
                ", animal='" + (animal != null ? animal.getNome() : "N/A") + '\'' +
                ", servico='" + servicoNome + '\'' +
                ", data='" + (data != null ? formatador.format(data) : "N/A") + '\'' +
                '}';
    }
}
