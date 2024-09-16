package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Candidato;
import com.urnavirtual.app.service.CandidatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidatoControllerTeste {

    @InjectMocks
    private CandidatoController candidatoController;

    @Mock
    private CandidatoService candidatoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCandidatoSuccess() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setFuncao("PREFEITO");

        // Simular o comportamento do serviço
        when(candidatoService.save(any(Candidato.class))).thenReturn(candidato.toString());

        // Chamada do método do controlador
        ResponseEntity<String> retorno = candidatoController.save(candidato);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(candidato.toString(), retorno.getBody());
    }

    @Test
    void saveCandidatoFailure() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setFuncao(null); // Simula uma falha na validação

        // Simular o comportamento do serviço para lançar uma exceção
        when(candidatoService.save(any(Candidato.class))).thenThrow(new Exception("Função é obrigatória!"));

        // Chamada do método do controlador
        ResponseEntity<String> retorno = candidatoController.save(candidato);

        // Verificação do resultado
        assertEquals(400, retorno.getStatusCodeValue());
        assertEquals(null, retorno.getBody());
    }

    @Test
    void deleteCandidatoSuccess() {
        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setFuncao("PREFEITO");

        // Simular o comportamento do serviço
        when(candidatoService.delete(anyLong())).thenReturn(candidato.toString());

        // Chamada do método do controlador
        ResponseEntity<String> retorno = candidatoController.delete(1L);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(candidato.toString(), retorno.getBody());
    }

    @Test
    void updateCandidatoSuccess() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setFuncao("VEREADOR");

        // Simular o comportamento do serviço
        when(candidatoService.update(anyLong(), any(Candidato.class))).thenReturn(candidato.toString());

        // Chamada do método do controlador
        ResponseEntity<String> retorno = candidatoController.update(1L, candidato);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(candidato.toString(), retorno.getBody());
    }

    @Test
    void updateCandidatoFailure() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setFuncao("PREFEITO");

        // Simular o comportamento do serviço para lançar uma exceção
        when(candidatoService.update(anyLong(), any(Candidato.class))).thenThrow(new Exception("Função é obrigatória!"));

        // Chamada do método do controlador
        ResponseEntity<String> retorno = candidatoController.update(1L, candidato);

        // Verificação do resultado
        assertEquals(400, retorno.getStatusCodeValue());
        assertEquals(null, retorno.getBody());
    }

    @Test
    void findAllCandidatosSuccess() {
        List<Candidato> candidatos = new ArrayList<>();
        candidatos.add(new Candidato());

        // Simular o comportamento do serviço
        when(candidatoService.findAll()).thenReturn(candidatos);

        // Chamada do método do controlador
        ResponseEntity<?> retorno = candidatoController.findAll();

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(candidatos, retorno.getBody());
    }

    @Test
    void findAllActivesSuccess() {
        List<Candidato> candidatosAtivos = new ArrayList<>();
        candidatosAtivos.add(new Candidato());

        // Simular o comportamento do serviço
        when(candidatoService.findAllActives()).thenReturn(candidatosAtivos);

        // Chamada do método do controlador
        ResponseEntity<?> retorno = candidatoController.findAllActives();

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(candidatosAtivos, retorno.getBody());
    }
}
