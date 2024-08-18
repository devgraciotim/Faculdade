package app.service;

import app.entity.Genre;
import app.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public String saveGenre(Genre genre){
        this.genreRepository.save(genre);
        return (
                "Gênero " + genre.getName() + " salvo com sucesso!"
        );
    }

    public List<Genre> findAllGenres(){
        List<Genre> genres = this.genreRepository.findAll();
        return genres;
    }

    public Genre findGenreById(Long id){
        Genre genre = this.genreRepository.findById(id).get();
        return genre;
    }

    public String updateGenre(Long id, Genre genre){
        genre.setId(id);
        this.genreRepository.save(genre);
        return (
                "Gênero " + genre.getName() + " atualizado com sucesso!"
        );
    }

    public String deleteGenre(Long id){
        this.genreRepository.deleteById(id);
        return (
                "Gênero com id " + id + " deletado com sucesso"
        );
    }
}
