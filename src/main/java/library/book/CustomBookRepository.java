package library.book;

import java.util.List;

public interface CustomBookRepository {

    public List<Book> findBooksTitle(String nation, String genre, String keyword);
    public List<Book> findBooksCategory(String nation, String genre, String keyword);
    public List<Book> findBooksPrice(String nation, String genre, Integer keyword);
}
