package com.urnavirtual.app.service;

import com.urnavirtual.app.entity.Candidato;
import com.urnavirtual.app.enums.StatusCandidato;
import com.urnavirtual.app.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;

    public String save(Candidato candidato) throws Exception {
        if (candidato.getFuncao() == null) {
            throw new Exception("Função é obrigatória!");
        }
        switch (candidato.getFuncao()) {
            case "1":
                candidato.setFuncao("PREFEITO");
                break;
            case "2":
                candidato.setFuncao("VEREADOR");
                break;
            default:
                throw new Exception("Função inválida!");
        }

        if (candidato.getStatus() != StatusCandidato.INATIVO) {
            candidato.setStatus(StatusCandidato.ATIVO);
        }

        this.candidatoRepository.save(candidato);
        return candidato.toString();
    }

    public String delete (Long id) {
        Candidato candidato = this.candidatoRepository.findById(id).get();
        candidato.setStatus(StatusCandidato.INATIVO);
        this.candidatoRepository.save(candidato);
        return candidato.toString();
    }

    public String update (Long id, Candidato candidato) throws Exception {
        candidato.setId(id);
        candidatoRepository.save(candidato);

        return candidato.toString();
    }

    public List<Candidato> findAll () {
        return this.candidatoRepository.findAll();
    }

    public List<Candidato> findAllActives() {
        List<Candidato> allCandidatos = candidatoRepository.findAll();
        List<Candidato> candidatosAtivos = new ArrayList<>();
        allCandidatos.forEach(candidato -> {
            if (candidato.getStatus() == StatusCandidato.ATIVO) {
                candidatosAtivos.add(candidato);
            }
        });
        return candidatosAtivos;
    }

    public List<Candidato> prefeitos() {
        List<Candidato> allCandidatos = this.candidatoRepository.findAll();
        List<Candidato> prefeitos = new ArrayList<>();
        allCandidatos.forEach(prefeito -> {
            if (prefeito.getFuncao().equals("PREFEITO") && prefeito.getStatus() == StatusCandidato.ATIVO) {
                prefeitos.add(prefeito);
            }
        });

        return prefeitos;
    }

    public List<Candidato> vereadores() {
        List<Candidato> allCandidatos = this.candidatoRepository.findAll();
        List<Candidato> vereadores = new ArrayList<>();
        allCandidatos.forEach(vereador -> {
            if (vereador.getFuncao().equals("VEREADOR") && vereador.getStatus() == StatusCandidato.ATIVO) {
                vereadores.add(vereador);
            }
        });

        return vereadores;
    }
}
