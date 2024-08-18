package app.controller;

import app.entity.Piece;
import app.service.PieceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/piece")
public class PieceController {
    @Autowired
    private PieceService pieceService;

    @PostMapping("/save")
    public ResponseEntity<?> savePiece(@Valid @RequestBody Piece piece) {
        try {
            String message = this.pieceService.savePiece(piece);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllPieces() {
        try {
            List<Piece> pieces = this.pieceService.findAllPieces();
            return new ResponseEntity<List<Piece>>(pieces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findPieceById(@PathVariable Long id) {
        try {
            Piece piece = this.pieceService.findPieceById(id);
            return new ResponseEntity<Piece>(piece, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePiece(@Valid @PathVariable Long id, @Valid @RequestBody Piece piece) {
        try {
            String message = this.pieceService.updatePiece(id, piece);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable Long id) {
        try {
            String message = this.pieceService.deletePiece(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
