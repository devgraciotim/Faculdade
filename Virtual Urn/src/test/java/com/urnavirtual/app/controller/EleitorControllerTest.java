package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.service.EleitorService;
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
public class EleitorControllerTeste {

    @InjectMocks
    private EleitorController eleitorController;

    @Mock
    private EleitorService eleitorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEleitorSuccess() throws Exception {
        Eleitor eleitor = new Eleitor();
        eleitor.setNome_completo("João Silva");

        // Simular o comportamento do serviço
        when(eleitorService.save(any(Eleitor.class))).thenReturn("Eleitor salvo\n" + eleitor.toString());

        // Chamada do método do controlador
        ResponseEntity<String> retorno = eleitorController.save(eleitor);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals("Eleitor salvo\n" + eleitor.toString(), retorno.getBody());
    }

    @Test
    void updateEleitorSuccess() throws Exception {
        Eleitor eleitor = new Eleitor();
        eleitor.setNome_completo("Maria Oliveira");

        // Simular o comportamento do serviço
        when(eleitorService.update(anyLong(), any(Eleitor.class)))
                .thenReturn("Eleitor atualizado\n" + eleitor.toString());

        // Chamada do método do controlador
        ResponseEntity<?> retorno = eleitorController.update(1L, eleitor);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals("Eleitor atualizado\n" + eleitor.toString(), retorno.getBody());
    }

    @Test
    void deleteEleitorSuccess() {
        // Simular o comportamento do serviço
        when(eleitorService.delete(anyLong())).thenReturn("Eleitor inativado\n");

        // Chamada do método do controlador
        ResponseEntity<String> retorno = eleitorController.save(1L);

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals("Eleitor inativado\n", retorno.getBody());
    }

    @Test
    void findAllEleitoresSuccess() {
        List<Eleitor> eleitores = new ArrayList<>();
        eleitores.add(new Eleitor());

        // Simular o comportamento do serviço
        when(eleitorService.findAll()).thenReturn(eleitores);

        // Chamada do método do controlador
        ResponseEntity<?> retorno = eleitorController.findAll();

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(eleitores, retorno.getBody());
    }

    @Test
    void findAllActivesSuccess() {
        List<Eleitor> eleitoresAtivos = new ArrayList<>();
        eleitoresAtivos.add(new Eleitor());

        // Simular o comportamento do serviço
        when(eleitorService.findAllActives()).thenReturn(eleitoresAtivos);

        // Chamada do método do controlador
        ResponseEntity<?> retorno = eleitorController.findAllActives();

        // Verificação do resultado
        assertEquals(200, retorno.getStatusCodeValue());
        assertEquals(eleitoresAtivos, retorno.getBody());
    }
}
