package library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    @Transactional
    public void add(BookDto udt) {
        bookRepository.save(udt.toEntity());
    }
    @Transactional
    public Book update(Long id, BookDto udt) {
        Book book = bookRepository.findById(id).get();
        book.update(udt.getTitle(), udt.getCategory(), udt.getNation(), udt.getGenre(), udt.getPrice());
        return book;
    }
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    public Book findBook(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }
    public List<Book> findAll() {
        List<Book> res = bookRepository.findAll();
        return res;
    }
    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
    @Transactional
    public List<Book> search(String keyword, String classification) {
        List<Book> res = null;
        if (classification.equals("제목")) {
            res = bookRepository.findBooksTitle(null,null, keyword);
        }
        else if(classification.equals("카테고리")) {
            res = bookRepository.findBooksCategory(null,null, keyword);
        }
        else if(classification.equals("가격")) {
            res = bookRepository.findBooksPrice(null,null, Integer.parseInt(keyword));
        }
        return res;
    }
    public List<Book> searchNation(String nation) {
        List<Book> res = bookRepository.findBooksTitle(nation, null, null);
        return res;
    }
    public List<Book> searchNationKeyword(String nation, String keyword, String classification) {
        List<Book> res = null;

        if (classification.equals("제목")) {
            res = bookRepository.findBooksTitle(nation, null, keyword);
        }
        else if(classification.equals("카테고리")) {
            res = bookRepository.findBooksCategory(nation, null, keyword);
        }
        else if(classification.equals("가격")) {
            res = bookRepository.findBooksPrice(nation, null, Integer.parseInt(keyword));
        }
        return res;
    }
    public List<Book> searchNationGenre(String nation, String genre) {
        List<Book> res = bookRepository.findBooksTitle(nation, genre, null);
        return res;
    }
    public List<Book> searchNationGenreKeyword(String nation, String genre, String keyword, String classification) {
        List<Book> res = null;

        if (classification.equals("제목")) {
            res = bookRepository.findBooksTitle(nation, genre, keyword);
        }
        else if(classification.equals("카테고리")) {
            res = bookRepository.findBooksCategory(nation, genre, keyword);
        }
        else if(classification.equals("가격")) {
            res = bookRepository.findBooksPrice(nation, genre, Integer.parseInt(keyword));
        }
        return res;
    }

    public String stringChange(String nation) {
        String res = "";

        if (nation.equals("일본")) {
            res = "일본만화";
        }
        else if (nation.equals("대한민국")) {
            res = "국내만화";
        }
        else if (nation.equals("미국")) {
            res = "미국만화";
        }
        return res;
    }
}
