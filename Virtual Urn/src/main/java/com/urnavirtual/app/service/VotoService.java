package com.urnavirtual.app.service;

import com.urnavirtual.app.entity.Apuracao;
import com.urnavirtual.app.entity.Candidato;
import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.entity.Voto;
import com.urnavirtual.app.enums.StatusCandidato;
import com.urnavirtual.app.enums.StatusEleitor;
import com.urnavirtual.app.repository.CandidatoRepository;
import com.urnavirtual.app.repository.EleitorRepository;
import com.urnavirtual.app.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService {
    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private EleitorRepository eleitorRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CandidatoService candidatoService;

    public String votar(Long id, Voto voto) throws Exception {
        // Verificar se o vereador é válido e tem a função correta
        Optional<Candidato> vereadorOpt = this.candidatoRepository.findById(voto.getVereador().getId());
        if (!vereadorOpt.isPresent()) {
            throw new Exception("Vereador invalido!");
        }
        Candidato vereador = vereadorOpt.get();
        if (!vereador.getFuncao().equals("VEREADOR") || vereador.getStatus() != StatusCandidato.ATIVO) {
            throw new Exception("O escolhido a vereador deve ter a função vereador e estar ativo");
        }

        // Verificar se o prefeito é válido e tem a função correta
        Optional<Candidato> prefeitoOpt = this.candidatoRepository.findById(voto.getPrefeito().getId());
        if (!prefeitoOpt.isPresent()) {
            throw new Exception("Prefeito invalido!");
        }
        Candidato prefeito = prefeitoOpt.get();
        if (!prefeito.getFuncao().equals("PREFEITO") || prefeito.getStatus() != StatusCandidato.ATIVO) {
            throw new Exception("O escolhido a prefeito deve ter a função prefeito e estar ativo");
        }

        // Verificar se o eleitor é válido e está apto para votar
        Optional<Eleitor> eleitorOpt = this.eleitorRepository.findById(id);
        if (!eleitorOpt.isPresent()) {
            throw new Exception("Eleitor invalido!");
        }
        Eleitor eleitor = eleitorOpt.get();
        if (eleitor.getStatus() != StatusEleitor.APTO) {
            throw new Exception("Eleitor deve ser APTO para votar");
        }

        if (eleitor.getStatus() == StatusEleitor.VOTOU) {
            throw new Exception("Eleitor já votou!");
        }

        // Atualizar status do eleitor e salvar o voto
        eleitor.setStatus(StatusEleitor.VOTOU);
        this.eleitorRepository.save(eleitor);
        this.votoRepository.save(voto);

        return voto.getHash();
    }

    public Apuracao realizarApuracao() {
        Apuracao apuracao = new Apuracao();
        List<Candidato> prefeitos = this.candidatoService.prefeitos();
        List<Candidato> vereadores = this.candidatoService.vereadores();

        prefeitos.forEach(prefeito -> {
            Long qntVotos = this.votoRepository.countVotosByCandidatoId(prefeito.getId());
            prefeito.setVotos_apurados(qntVotos);
        });

        vereadores.forEach(vereador -> {
            Long qntVotos = this.votoRepository.countVotosByCandidatoId(vereador.getId());
            vereador.setVotos_apurados(qntVotos);
        });

        apuracao.setPrefeitos(prefeitos);
        apuracao.setVereadores(vereadores);

        apuracao.setTotal_votos(this.votoRepository.countTotalVotos());

        return apuracao;
    }
}
