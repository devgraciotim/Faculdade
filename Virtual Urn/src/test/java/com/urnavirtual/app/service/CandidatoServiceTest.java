package com.urnavirtual.app.service;

import com.urnavirtual.app.entity.Candidato;
import com.urnavirtual.app.enums.StatusCandidato;
import com.urnavirtual.app.repository.CandidatoRepository;
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
public class CandidatoServiceTeste {

    @InjectMocks
    private CandidatoService candidatoService;

    @Mock
    private CandidatoRepository candidatoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCandidatoSuccess() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setFuncao("1");
        candidato.setStatus(StatusCandidato.ATIVO);

        when(candidatoRepository.save(any(Candidato.class))).thenReturn(candidato);

        String result = candidatoService.save(candidato);

        assertEquals(candidato.toString(), result);
        verify(candidatoRepository).save(candidato);
    }

    @Test
    void saveCandidatoFailure() {
        Candidato candidato = new Candidato();
        candidato.setFuncao(null);

        Exception exception = assertThrows(Exception.class, () -> {
            candidatoService.save(candidato);
        });

        assertEquals("Função é obrigatória!", exception.getMessage());
    }

    @Test
    void deleteCandidatoSuccess() {
        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setFuncao("PREFEITO");
        candidato.setStatus(StatusCandidato.ATIVO);

        when(candidatoRepository.findById(anyLong())).thenReturn(Optional.of(candidato));
        when(candidatoRepository.save(any(Candidato.class))).thenReturn(candidato);

        String result = candidatoService.delete(1L);

        assertEquals(candidato.toString(), result);
        assertEquals(StatusCandidato.INATIVO, candidato.getStatus());
        verify(candidatoRepository).save(candidato);
    }

    @Test
    void updateCandidatoSuccess() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setFuncao("VEREADOR");

        when(candidatoRepository.save(any(Candidato.class))).thenReturn(candidato);

        String result = candidatoService.update(1L, candidato);

        assertEquals(candidato.toString(), result);
        verify(candidatoRepository).save(candidato);
    }

    @Test
    void findAllSuccess() {
        List<Candidato> candidatos = new ArrayList<>();
        candidatos.add(new Candidato());

        when(candidatoRepository.findAll()).thenReturn(candidatos);

        List<Candidato> result = candidatoService.findAll();

        assertEquals(candidatos, result);
        verify(candidatoRepository).findAll();
    }

    @Test
    void findAllActivesSuccess() {
        List<Candidato> allCandidatos = new ArrayList<>();
        Candidato ativo = new Candidato();
        ativo.setStatus(StatusCandidato.ATIVO);
        allCandidatos.add(ativo);
        allCandidatos.add(new Candidato());

        when(candidatoRepository.findAll()).thenReturn(allCandidatos);

        List<Candidato> result = candidatoService.findAllActives();

        assertEquals(1, result.size());
        assertTrue(result.contains(ativo));
        verify(candidatoRepository).findAll();
    }

    @Test
    void prefeitosSuccess() {
        List<Candidato> allCandidatos = new ArrayList<>();
        Candidato prefeito = new Candidato();
        prefeito.setFuncao("PREFEITO");
        prefeito.setStatus(StatusCandidato.ATIVO);
        allCandidatos.add(prefeito);
        allCandidatos.add(new Candidato());

        when(candidatoRepository.findAll()).thenReturn(allCandidatos);

        List<Candidato> result = candidatoService.prefeitos();

        assertEquals(1, result.size());
        //verify(candidatoRepository).findAll();
    }

    @Test
    void vereadoresSuccess() {
        List<Candidato> allCandidatos = new ArrayList<>();
        Candidato vereador = new Candidato();
        vereador.setFuncao("VEREADOR");
        vereador.setStatus(StatusCandidato.ATIVO);
        allCandidatos.add(vereador);
        allCandidatos.add(new Candidato());

        when(candidatoRepository.findAll()).thenReturn(allCandidatos);

        List<Candidato> result = candidatoService.vereadores();

        assertEquals(1, result.size());
        assertTrue(result.contains(vereador));
        verify(candidatoRepository).findAll();
    }
}
