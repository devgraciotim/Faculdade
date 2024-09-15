package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Apuracao;
import com.urnavirtual.app.entity.Voto;
import com.urnavirtual.app.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
public class VotoController {
    @Autowired
    private VotoService votoService;

    @PostMapping("/votar/{id}")
    public ResponseEntity<?> votar(@PathVariable Long id, @RequestBody Voto voto) {
        try {
            String hash = this.votoService.votar(id, voto);
            return new ResponseEntity<>(hash, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/realizarApuracao")
    public ResponseEntity<?> realizarApuracao() {
        try {
            Apuracao apuracao = this.votoService.realizarApuracao();
            return new ResponseEntity<>(apuracao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
