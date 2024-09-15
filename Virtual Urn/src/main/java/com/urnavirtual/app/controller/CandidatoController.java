package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Candidato;
import com.urnavirtual.app.service.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid Candidato candidato) {
        try {
            String message = this.candidatoService.save(candidato);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            String message = this.candidatoService.delete(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody @Valid Candidato candidato) {
        try {
            String message = this.candidatoService.update(id, candidato);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Candidato> candidatos = this.candidatoService.findAll();
            return new ResponseEntity<>(candidatos, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllActives")
    public ResponseEntity<?> findAllActives() {
        try {
            List<Candidato> candidatos = this.candidatoService.findAllActives();
            return new ResponseEntity<>(candidatos, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
