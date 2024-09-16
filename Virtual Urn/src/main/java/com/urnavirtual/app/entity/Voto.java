package com.urnavirtual.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime data_hora;

    @NotNull
    @ManyToOne
    private Candidato prefeito;

    @NotNull
    @ManyToOne
    private Candidato vereador;

    @NotNull
    @Column(unique = true)
    private String hash;

    @PrePersist
    public void prePersist() {
        this.data_hora = LocalDateTime.now();
        this.hash = gerarHash();
    }

    private String gerarHash() {
        return UUID.randomUUID().toString();
    }
}
