package com.urnavirtual.app.entity;

import com.urnavirtual.app.enums.StatusCandidato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome_completo;

    @CPF
    private String cpf;

    @Column(unique = true, nullable = false)
    private Long numero;

    @NotNull
    private String funcao;

    @Enumerated(EnumType.STRING)
    private StatusCandidato status;

    @Transient
    private Long votos_apurados;
}
