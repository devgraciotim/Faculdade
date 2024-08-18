package app.service;

import app.entity.*;
import app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PieceService {
    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PieceTypeRepository pieceTypeRepository;

    public String savePiece(Piece piece){
        try {
            if (piece.getPublisher().getId() != null) {
                Publisher publisher = this.publisherRepository.findById(piece.getPublisher().getId()).get();
                piece.setPublisher(publisher);
            }

            List<Author> updatedAuthors = new ArrayList<>();
            piece.getAuthors().forEach(author -> {
                Author authorPiece = this.authorRepository.findById(author.getId()).get();
                updatedAuthors.add(authorPiece);
            });
            piece.setAuthors(updatedAuthors);

            List<Genre> updatedGenres = new ArrayList<>();
            piece.getGenres().forEach(genre -> {
                Genre genrePiece = this.genreRepository.findById(genre.getId()).get();
                updatedGenres.add(genrePiece);
            });
            piece.setGenres(updatedGenres);

            PieceType pieceType = this.pieceTypeRepository.findById(piece.getPiece_type().getId()).get();
            piece.setPiece_type(pieceType);

            this.pieceRepository.save(piece);
            return (
                    "Obra " + piece.getTitle() + " salva com sucesso!"
            );
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<Piece> findAllPieces(){
        return this.pieceRepository.findAll();
    }

    public Piece findPieceById(Long id){
        return this.pieceRepository.findById(id).get();
    }

    public String updatePiece(Long id, Piece piece){
        piece.setId(id);
        this.pieceRepository.save(piece);
        return (
                "Obra " + piece.getTitle() + " atualizada com sucesso!"
        );
    }

    public String deletePiece(Long id){
        this.pieceRepository.deleteById(id);
        return (
                "Obra com id " + id + " deletada com sucesso"
        );
    }
}
