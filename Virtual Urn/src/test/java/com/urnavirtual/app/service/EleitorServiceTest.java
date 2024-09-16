package com.urnavirtual.app.service;

import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.enums.StatusEleitor;
import com.urnavirtual.app.repository.EleitorRepository;
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
public class EleitorServiceTest {

    @InjectMocks
    private EleitorService eleitorService;

    @Mock
    private EleitorRepository eleitorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveWithValidData() {
        Eleitor eleitor = new Eleitor();
        eleitor.setEmail("test@example.com");
        eleitor.setCpf("12345678900");
        eleitor.setStatus(StatusEleitor.PENDENTE);

        when(eleitorRepository.save(any(Eleitor.class))).thenReturn(eleitor);

        String result = eleitorService.save(eleitor);

        assertEquals("Eleitor salvo\n" + eleitor.toString(), result);
        assertEquals(StatusEleitor.APTO, eleitor.getStatus());
        verify(eleitorRepository).save(eleitor);
    }

    @Test
    void saveWithMissingData() {
        Eleitor eleitor = new Eleitor();
        eleitor.setEmail(null);
        eleitor.setCpf("12345678900");
        eleitor.setStatus(StatusEleitor.PENDENTE);

        when(eleitorRepository.save(any(Eleitor.class))).thenReturn(eleitor);

        String result = eleitorService.save(eleitor);

        assertEquals("Eleitor salvo\n" + eleitor.toString(), result);
        assertEquals(StatusEleitor.PENDENTE, eleitor.getStatus());
        verify(eleitorRepository).save(eleitor);
    }

    @Test
    void findAll() {
        List<Eleitor> eleitores = new ArrayList<>();
        Eleitor eleitor = new Eleitor();
        eleitores.add(eleitor);

        when(eleitorRepository.findAll()).thenReturn(eleitores);

        List<Eleitor> result = eleitorService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(eleitorRepository).findAll();
    }

    @Test
    void delete() {
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setStatus(StatusEleitor.APTO);

        when(eleitorRepository.findById(anyLong())).thenReturn(Optional.of(eleitor));
        when(eleitorRepository.save(any(Eleitor.class))).thenReturn(eleitor);

        String result = eleitorService.delete(1L);

        assertEquals("Eleitor inativado\n" + eleitor.toString(), result);
        assertEquals(StatusEleitor.INATIVO, eleitor.getStatus());
        verify(eleitorRepository).findById(1L);
        verify(eleitorRepository).save(eleitor);
    }

    @Test
    void findAllActives() {
        List<Eleitor> allEleitores = new ArrayList<>();
        Eleitor ativo = new Eleitor();
        ativo.setStatus(StatusEleitor.APTO);
        Eleitor inativo = new Eleitor();
        inativo.setStatus(StatusEleitor.INATIVO);
        allEleitores.add(ativo);
        allEleitores.add(inativo);

        when(eleitorRepository.findAll()).thenReturn(allEleitores);

        List<Eleitor> result = eleitorService.findAllActives();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(StatusEleitor.APTO, result.get(0).getStatus());
        verify(eleitorRepository).findAll();
    }

    @Test
    void update() throws Exception {
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setEmail("update@example.com");
        eleitor.setCpf("09876543210");
        eleitor.setStatus(StatusEleitor.PENDENTE);

        when(eleitorRepository.save(any(Eleitor.class))).thenReturn(eleitor);
        when(eleitorRepository.findById(anyLong())).thenReturn(Optional.of(eleitor));

        String result = eleitorService.update(1L, eleitor);

        assertEquals("Eleitor atualizado\n" + Optional.of(eleitor), result);
        assertEquals(1L, eleitor.getId());
        verify(eleitorRepository).save(eleitor);
        verify(eleitorRepository).findById(1L);
    }
}
