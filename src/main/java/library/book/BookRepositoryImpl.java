package library.book;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static library.book.QBook.book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements CustomBookRepository{
    private final JPAQueryFactory queryFactory;

    public List<Book> findBooksTitle(String nation, String genre, String keyword) {
        List<Book> res = queryFactory
                .selectFrom(book)
                .where(eqNation(nation), eqGenre(genre), eqTitle(keyword))
                .fetch();
        return res;
    }
    public List<Book> findBooksCategory(String nation, String genre, String keyword) {
        List<Book> res = queryFactory
                .selectFrom(book)
                .where(eqNation(nation), eqGenre(genre), eqCategory(keyword))
                .fetch();
        return res;
    }
    public List<Book> findBooksPrice(String nation, String genre, Integer keyword) {
        List<Book> res = queryFactory
                .selectFrom(book)
                .where(eqNation(nation), eqGenre(genre), eqPrice(keyword))
                .fetch();
        return res;
    }

    private BooleanExpression eqNation(String nation) {
        if (StringUtils.isEmpty(nation)) {
            return null;
        }
        return book.nation.eq(nation);
    }

    private BooleanExpression eqGenre(String genre) {
        if (StringUtils.isEmpty(genre)) {
            return null;
        }
        return book.genre.eq(genre);
    }

    private BooleanExpression eqPrice(Integer price) {
        if (price == null) {
            return null;
        }
        return book.price.loe(price);
    }

    private BooleanExpression eqTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return book.title.contains(title);
    }

    private BooleanExpression eqCategory(String category) {
        if (StringUtils.isEmpty(category)) {
            return null;
        }
        return book.category.contains(category);
    }

}
