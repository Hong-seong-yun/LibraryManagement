package library.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, CustomBookRepository {

//  home의 title, category, price
//    @Query("select b from Book b where b.title LIKE %:keyword%")
//    List<Book> findHomeTitle(@Param("keyword") String keyword);
//
//    @Query("select b from Book b where b.category LIKE %:keyword%")
//    List<Book> findHomeCategory(@Param("keyword") String keyword);
//
//    @Query("select b from Book b where b.price <= :keyword")
//    List<Book> findHomePrice(@Param("keyword") Integer keyword);
//
//
//    @Query("select b from Book b where b.nation = :nation and b.genre = :genre")
//    List<Book> findMenu(@Param("nation") String nation, @Param("genre") String genre);
//    @Query("select b from Book b where b.nation = :nation")
//    List<Book> findNation(@Param("nation") String nation);
//
////  대분류,소분류별 title, category, price
//    @Query("select b from Book b where b.nation = :nation and b.genre = :genre and b.title LIKE %:keyword%")
//    List<Book> searchCategoryTitle(@Param("nation") String nation, @Param("genre") String genre, @Param("keyword") String keyword);
//    //  category
//    @Query("select b from Book b where b.nation = :nation and b.genre = :genre and b.category LIKE %:keyword%")
//    List<Book> searchCategoryCategory(@Param("nation") String nation, @Param("genre") String genre, @Param("keyword") String keyword);
//    //  가격이하일때
//    @Query("select b from Book b where b.nation = :nation and b.genre = :genre and b.price <= :keyword")
//    List<Book> searchCategoryPrice(@Param("nation") String nation, @Param("genre") String genre, @Param("keyword") Integer keyword);
//
//
////  title
//    @Query("select b from Book b where b.nation = :nation and b.title LIKE %:keyword%")
//    List<Book> searchNationTitle(@Param("nation") String nation, @Param("keyword") String keyword);
////  category
//    @Query("select b from Book b where b.nation = :nation and b.category LIKE %:keyword%")
//    List<Book> searchNationCategory(@Param("nation") String nation, @Param("keyword") String keyword);
////  가격이하일때
//    @Query("select b from Book b where b.nation = :nation and b.price <= :keyword")
//    List<Book> searchNationPrice(@Param("nation") String nation, @Param("keyword") Integer keyword);
}
