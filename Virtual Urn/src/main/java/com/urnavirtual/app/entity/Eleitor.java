package com.urnavirtual.app.entity;

import com.urnavirtual.app.enums.StatusCandidato;
import com.urnavirtual.app.enums.StatusEleitor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Eleitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome_completo;

    @CPF
    private String cpf;

    @NotBlank
    private String profissao;

    @Pattern(regexp = "^\\d{2} 9\\d{4}-\\d{4}$", message = "Telefone Celular inválido!")
    @NotBlank
    private String telefone_celular;

    @Pattern(regexp = "^\\d{2} 9\\d{4}-\\d{4}$", message = "Telefone Fixo inválido!")
    private String telefone_fixo;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private StatusEleitor status;
}
