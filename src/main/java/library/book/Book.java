package library.book;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String nation;
    @Column(nullable = false)
    private String genre;
    private Integer price;
    private String insertDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

    @Builder
    public Book(String title, String category, String nation, String genre, Integer price) {
        this.title = title;
        this.category = category;
        this.nation = nation;
        this.genre = genre;
        this.price = price;
    }
    public void update(String title, String category, String nation, String genre, Integer price) {
        this.title = title;
        this.category = category;
        this.nation = nation;
        this.genre = genre;
        this.price = price;
    }
}
