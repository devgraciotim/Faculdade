package app.service;

import app.entity.Author;
import app.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PieceService pieceService;

    public String saveAuthor(Author author){

        this.authorRepository.save(author);
        return (
                "Autor " + author.getName() + " salvo com sucesso!"
        );
    }

    public List<Author> findAllAuthors(){
        List<Author> authors = this.authorRepository.findAll();
        return authors;
    }

    public Author findAuthorById(Long id){
        Author author = this.authorRepository.findById(id).get();
        return author;
    }

    public String updateAuthor(Long id, Author author){
        author.setId(id);
        this.authorRepository.save(author);
        return (
                "Autor " + author.getName() + " atualizado com sucesso!"
        );
    }

    public String deleteAuthor(Long id){
        this.authorRepository.deleteById(id);
        return (
                "Autor com id " + id + " deletado com sucesso"
        );
    }
}
