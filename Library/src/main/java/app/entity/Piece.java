package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "título não pode estar em branco")
    private String title;

    private int publication_year;

    @NotNull(message = "preço não pode ser vazio")
    @Positive(message = "preço deve ser positivo")
    private Double price;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("pieces")
    @JoinColumn(name = "publisher_id")
    @NotNull(message = "é necessário definir uma editora")
    private Publisher publisher;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("pieces")
    @JoinTable(
            name = "piece_author",
            joinColumns = @JoinColumn(name = "piece_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @NotEmpty(message = "é necessário definir um autor")
    private List<Author> authors;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("pieces")
    @JoinTable(
            name = "piece_genre",
            joinColumns = @JoinColumn(name = "piece_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @NotEmpty(message = "é necessário definir um genero")
    private List<Genre> genres;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("piece")
    @NotNull(message = "é necessário definir um tipo")
    private PieceType piece_type;

    @ManyToMany(mappedBy = "items")
    private List<Sale> sales;
}