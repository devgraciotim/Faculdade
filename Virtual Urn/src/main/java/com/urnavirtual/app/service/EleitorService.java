package com.urnavirtual.app.service;

import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.enums.StatusEleitor;
import com.urnavirtual.app.repository.EleitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EleitorService {
    @Autowired
    private EleitorRepository eleitorRepository;

    public String save(Eleitor eleitor) {
        if (eleitor.getEmail() != null && eleitor.getCpf() != null) {
            eleitor.setStatus(StatusEleitor.APTO);
        } else if (eleitor.getEmail()  == null || eleitor.getCpf() == null ) {
            eleitor.setStatus(StatusEleitor.PENDENTE);
        }

        this.eleitorRepository.save(eleitor);
        return "Eleitor salvo\n" +  eleitor.toString();
    }

    public List<Eleitor> findAll() {
        return this.eleitorRepository.findAll();
    }

    public String delete(Long id) {
        Eleitor eleitor = this.eleitorRepository.findById(id).get();
        eleitor.setStatus(StatusEleitor.INATIVO);

        this.eleitorRepository.save(eleitor);

        return "Eleitor inativado\n" + eleitor.toString();
    }

    public List<Eleitor> findAllActives() {
        List<Eleitor> allEleitores = eleitorRepository.findAll();
        List<Eleitor> eleitoresAtivos = new ArrayList<>();

        allEleitores.forEach(eleitor -> {
            if (eleitor.getStatus() != StatusEleitor.INATIVO) {
                eleitoresAtivos.add(eleitor);
            }
        });

        return eleitoresAtivos;
    }

    public String update(Long id, Eleitor eleitor) throws Exception {
        eleitor.setId(id);

        this.save(eleitor);
        return "Eleitor atualizado\n" + this.eleitorRepository.findById(id);
    }
}
