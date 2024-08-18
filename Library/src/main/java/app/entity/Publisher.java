package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome da editora não pode ser vazio")
    private String name;

    @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "O CEP deve estar no formato 99999-999 ou 99999999"
    )
    private String cep;

    @Pattern(
            regexp = "^\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}$",
            message = "O CNPJ deve estar no formato 99.999.999/9999-99 ou 99999999999999, com ou sem pontuação"
    )
    private String cnpj;

    @Pattern(
            regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$|^\\d{4,5}-?\\d{4}$",
            message = "O número de telefone deve seguir o formato com ou sem (XX) XXXXX-XXXX, (XX) XXXX-XXXX, XXXXX-XXXX ou XXXX-XXXX"
    )
    private String phone;

    @OneToMany(mappedBy = "publisher")
    private List<Piece> pieces;
}
