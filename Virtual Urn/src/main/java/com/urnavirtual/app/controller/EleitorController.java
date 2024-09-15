package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.service.EleitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eleitor")
public class EleitorController {
    @Autowired
    private EleitorService eleitorService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody Eleitor eleitor) {
        try {
            String message = this.eleitorService.save(eleitor);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> save(@PathVariable Long id) {
        try {
            String message = this.eleitorService.delete(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Eleitor> eleitores = this.eleitorService.findAll();
            return new ResponseEntity<List<Eleitor>>(eleitores, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllActives")
    public ResponseEntity<?> findAllActives() {
        try {
            List<Eleitor> eleitores = this.eleitorService.findAllActives();
            return new ResponseEntity<List<Eleitor>>(eleitores, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable @Valid Long id, @RequestBody @Valid Eleitor eleitor) {
        try {
            String message = this.eleitorService.update(id, eleitor);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
