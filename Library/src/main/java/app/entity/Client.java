package app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome do cliente não pode ser vazio")
    private String name;

    @CPF
    private String cpf;

    @Positive(message = "idade deve ser positiva")
    private int age;

    @NotBlank(message = "endereço não pode ser vazio")
    private String address;

    @Email(message = "email deve ser valido exemplo@exemplo.com")
    private String email;

    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "O CEP deve estar no formato 99999-999 ou 99999999"
    )
    private String cep;

    @Pattern(
            regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$|^\\d{4,5}-?\\d{4}$",
            message = "O número de telefone deve seguir o formato com ou sem (XX) XXXXX-XXXX, (XX) XXXX-XXXX, XXXXX-XXXX ou XXXX-XXXX"
    )
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Sale> sales;
}
