package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    @NotNull(message = "cliente não pode ser vazio")
    @JsonIgnoreProperties("sales")
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("sales")
    private Employee employee;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("sales")
    @JoinTable(
            name = "sale_piece",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "piece_id")
    )
    @NotEmpty(message = "é necessário selecionar ao menos 1 item")
    private List<Piece> items;

    @Positive(message = "valor total não pode ser negativo")
    private Double total_value;

    @NotBlank(message = "Observação não pode ser vazia")
    private String observation;
}
