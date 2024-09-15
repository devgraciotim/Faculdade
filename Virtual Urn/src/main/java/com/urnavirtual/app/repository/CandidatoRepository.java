package com.urnavirtual.app.repository;

import com.urnavirtual.app.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
