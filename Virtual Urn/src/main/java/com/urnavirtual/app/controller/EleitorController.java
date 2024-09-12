package com.urnavirtual.app.controller;

import com.urnavirtual.app.entity.Eleitor;
import com.urnavirtual.app.service.EleitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
