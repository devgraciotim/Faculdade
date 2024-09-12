package com.urnavirtual.app.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apuracao {
    private Long total_votos;
    private List<Candidato> vereadores;
    private List<Candidato> prefeitos;
}
