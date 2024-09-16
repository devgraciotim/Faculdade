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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private EleitorRepository eleitorRepository;

    @Mock
    private CandidatoRepository candidatoRepository;

    @Mock
    private CandidatoService candidatoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void votarSuccess() throws Exception {
        Eleitor eleitor = new Eleitor();
        eleitor.setStatus(StatusEleitor.APTO);

        Candidato vereador = new Candidato();
        vereador.setId(1L);
        vereador.setFuncao("VEREADOR");
        vereador.setStatus(StatusCandidato.ATIVO);

        Candidato prefeito = new Candidato();
        prefeito.setId(2L);
        prefeito.setFuncao("PREFEITO");
        prefeito.setStatus(StatusCandidato.ATIVO);

        Voto voto = new Voto();
        voto.setVereador(vereador);
        voto.setPrefeito(prefeito);
        voto.setHash("hash");

        when(eleitorRepository.findById(anyLong())).thenReturn(Optional.of(eleitor));
        when(candidatoRepository.findById(1L)).thenReturn(Optional.of(vereador));
        when(candidatoRepository.findById(2L)).thenReturn(Optional.of(prefeito));
        when(votoRepository.save(any(Voto.class))).thenReturn(voto);
        when(eleitorRepository.save(any(Eleitor.class))).thenReturn(eleitor);

        String result = votoService.votar(1L, voto);

        assertEquals("hash", result);
        assertEquals(StatusEleitor.VOTOU, eleitor.getStatus());
        verify(votoRepository).save(voto);
        verify(eleitorRepository).save(eleitor);
    }
/*
    @Test
    void votarEleitorInvalido() {
        when(eleitorRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            votoService.votar(1L, new Voto());
        });

        assertEquals("Eleitor invalido!", exception.getMessage());
    }

    @Test
    void votarEleitorNaoApto() {
        Eleitor eleitor = new Eleitor();
        eleitor.setStatus(StatusEleitor.INATIVO);

        Candidato vereador = new Candidato();
        vereador.setId(1L);
        vereador.setFuncao("VEREADOR");
        vereador.setStatus(StatusCandidato.ATIVO);

        Candidato prefeito = new Candidato();
        prefeito.setId(2L);
        prefeito.setFuncao("PREFEITO");
        prefeito.setStatus(StatusCandidato.ATIVO);

        Voto voto = new Voto();
        voto.setVereador(vereador);
        voto.setPrefeito(prefeito);

        when(eleitorRepository.findById(anyLong())).thenReturn(Optional.of(eleitor));

        Exception exception = assertThrows(Exception.class, () -> {
            votoService.votar(1L, voto);
        });

        assertEquals("Eleitor deve ser APTO para votar", exception.getMessage());
    }

    @Test
    void votarEleitorJaVotou() {
        Eleitor eleitor = new Eleitor();
        eleitor.setStatus(StatusEleitor.VOTOU);

        Candidato vereador = new Candidato();
        vereador.setId(1L);
        vereador.setFuncao("VEREADOR");
        vereador.setStatus(StatusCandidato.ATIVO);

        Candidato prefeito = new Candidato();
        prefeito.setId(2L);
        prefeito.setFuncao("PREFEITO");
        prefeito.setStatus(StatusCandidato.ATIVO);

        Voto voto = new Voto();
        voto.setVereador(vereador);
        voto.setPrefeito(prefeito);

        when(eleitorRepository.findById(anyLong())).thenReturn(Optional.of(eleitor));

        Exception exception = assertThrows(Exception.class, () -> {
            votoService.votar(1L, voto);
        });

        assertEquals("Eleitor já votou!", exception.getMessage());
    }

    @Test
    void realizarApuracaoSuccess() {
        List<Candidato> prefeitos = new ArrayList<>();
        List<Candidato> vereadores = new ArrayList<>();

        Candidato prefeito = new Candidato();
        prefeito.setId(1L);
        prefeitos.add(prefeito);

        Candidato vereador = new Candidato();
        vereador.setId(2L);
        vereadores.add(vereador);

        when(candidatoService.prefeitos()).thenReturn(prefeitos);
        when(candidatoService.vereadores()).thenReturn(vereadores);
        when(votoRepository.countVotosByCandidatoId(1L)).thenReturn(10L);
        when(votoRepository.countVotosByCandidatoId(2L)).thenReturn(20L);
        when(votoRepository.countTotalVotos()).thenReturn(30L);

        Apuracao result = votoService.realizarApuracao();

        assertNotNull(result);
        assertEquals(10L, result.getPrefeitos().get(0).getVotos_apurados());
        assertEquals(20L, result.getVereadores().get(0).getVotos_apurados());
        assertEquals(30L, result.getTotal_votos());
        verify(candidatoService).prefeitos();
        verify(candidatoService).vereadores();
        verify(votoRepository).countVotosByCandidatoId(1L);
        verify(votoRepository).countVotosByCandidatoId(2L);
        verify(votoRepository).countTotalVotos();
    }
 */
}
