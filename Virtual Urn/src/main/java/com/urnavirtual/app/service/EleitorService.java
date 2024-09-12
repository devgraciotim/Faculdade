package com.urnavirtual.app.service;

import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.enums.StatusEleitor;
import com.urnavirtual.app.repository.EleitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleitorService {
    @Autowired
    private EleitorRepository eleitorRepository;

    public String save(Eleitor eleitor) {
        if (eleitor.getEmail() != null && eleitor.getCpf() != null) {
            eleitor.setStatus(StatusEleitor.APTO);
        } else {
            eleitor.setStatus(StatusEleitor.PENDENTE);
        }

        this.eleitorRepository.save(eleitor);
        return "Eleitor salvo\n" +  eleitor.toString();
    }

    public String delete(Long id) {
        Eleitor eleitor = this.eleitorRepository.findById(id).get();
        eleitor.setStatus(StatusEleitor.INATIVO);

        return "Eleitor inativado\n" + eleitor.toString();
    }

    public List<Eleitor> findAll() {
        return this.eleitorRepository.findAll();
    }
}
