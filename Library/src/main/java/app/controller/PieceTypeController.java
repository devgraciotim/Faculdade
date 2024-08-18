package app.controller;

import app.entity.PieceType;
import app.service.PieceTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieceType")
public class PieceTypeController {
    @Autowired
    private PieceTypeService pieceTypeService;

    @PostMapping("/save")
    public ResponseEntity<?> savePieceType(@Valid @RequestBody PieceType pieceType) {
        try {
            String message = this.pieceTypeService.savePieceType(pieceType);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllPieceTypes() {
        try {
            List<PieceType> pieceTypes = this.pieceTypeService.findAllPieceTypes();
            return new ResponseEntity<List<PieceType>>(pieceTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findPieceTypeById(@PathVariable Long id) {
        try {
            PieceType pieceType = this.pieceTypeService.findPieceTypeById(id);
            return new ResponseEntity<PieceType>(pieceType, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePieceType(@PathVariable Long id, @RequestBody PieceType pieceType) {
        try {
            String message = this.pieceTypeService.updatePieceType(id, pieceType);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePieceType(@PathVariable Long id) {
        try {
            String message = this.pieceTypeService.deletePieceType(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
