package library.book;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BookDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotBlank(message = "제목을 입력하세요")
    private String title;
    @NotBlank(message = "카테고리를 입력하세요")
    private String category;
    @NotBlank(message = "국가를 입력하세요")
    private String nation;
    @NotBlank(message = "장르를 입력하세요")
    private String genre;
    @NotNull(message = "가격을 입력하세요")
    private Integer price;
    private String insertDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

    @Builder
    public BookDto(String title, String category, String nation, String genre, Integer price) {
        this.title = title;
        this.category = category;
        this.nation = nation;
        this.genre = genre;
        this.price = price;
    }

    public Book toEntity() {
        Book build = Book.builder()
                .title(title)
                .category(category)
                .genre(genre)
                .nation(nation)
                .price(price)
                .build();
        return build;
    }
}
