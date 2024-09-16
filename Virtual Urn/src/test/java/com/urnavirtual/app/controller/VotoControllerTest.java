package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Apuracao;
import com.urnavirtual.app.entity.Candidato;
import com.urnavirtual.app.entity.Voto;
import com.urnavirtual.app.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VotoControllerTeste {

    @InjectMocks
    private VotoController votoController;

    @Mock
    private VotoService votoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void votoBemSucedito() throws Exception {
        // Criação de candidatos
        Candidato prefeito = new Candidato();
        prefeito.setId(1L);

        Candidato vereador = new Candidato();
        vereador.setId(4L);

        // Criação de voto
        Voto voto = new Voto();
        voto.setPrefeito(prefeito);
        voto.setVereador(vereador);

        // Simulação do retorno do serviço
        String hashEsperado = "dummy-hash";
        when(votoService.votar(1L, voto)).thenReturn(hashEsperado);

        // Chamada do método do controlador
        ResponseEntity<?> retorno = votoController.votar(1L, voto);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(hashEsperado, retorno.getBody());
    }

    @Test
    void realizarApuracaoBemSucedido() throws Exception {
        // Criação de dados de apuração
        Apuracao apuracao = new Apuracao();
        apuracao.setTotal_votos(100L);
        apuracao.setPrefeitos(List.of(new Candidato()));
        apuracao.setVereadores(List.of(new Candidato()));

        // Simulação do retorno do serviço
        when(votoService.realizarApuracao()).thenReturn(apuracao);

        // Chamada do método do controlador
        ResponseEntity<?> retorno = votoController.realizarApuracao();

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(apuracao, retorno.getBody());
    }
}
