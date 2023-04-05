package library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    // 홈
    @GetMapping
    public String root() {
        return "redirect:/home";
    }
    //목록
    @GetMapping("/home")
    public String books(Model model, String keyword, String classification) {
        List<Book> list = null;
        if (keyword == null) {
            list = bookService.findAll();
            // 최근 입력된 순서
            Collections.reverse(list);
            int size = list.size();
            if (size < 5) {
                list = list.subList(0,size);
            }
            else {
                // 5개까지만 출력
                list = list.subList(0,5);
            }
        }
        else {
            list = bookService.search(keyword, classification);
        }
        model.addAttribute("searchList", list);
        return "home.html";
    }
    // 생성
    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute(BookDto.builder().build().toEntity());
        return "addForm.html";
    }

    @PostMapping("/addForm")
    public String add(@Valid BookDto dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("book", dto);
            Map<String, String> validatorResult = bookService.validateHandling(errors);
            for(String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "addForm.html";
        }
        bookService.add(dto);
        return "redirect:/home";
    }

    // 상세
    @GetMapping("/home/{id}")
    public String book(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "book.html";
    }
    // 삭제
    @GetMapping("/delete/{id}")
    public String bookDelete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/home";
    }
    // 수정
    @GetMapping("/editForm/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "editForm.html";
    }
    @PostMapping("/editForm/{id}")
    public String bookUpdate(@PathVariable("id") Long id, @Valid BookDto dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Book original = bookService.findBook(id);
            model.addAttribute("book", original);
            Map<String, String> validatorResult = bookService.validateHandling(errors);
            for(String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "editForm.html";
        }
        bookService.update(id, dto);
        return "redirect:/home";
    }

    @GetMapping("/search/{nation}")
    public String searchNation(@PathVariable("nation") String nation, Model model, String keyword, String classification) {
        List<Book> books = null;
        if (keyword == null) {
            books = bookService.searchNation(nation);
        }
        else {
            books = bookService.searchNationKeyword(nation, keyword, classification);
        }
        String cnt = bookService.stringChange(nation);
        model.addAttribute("books", books);
        model.addAttribute("category", cnt);
        model.addAttribute("nara", nation);
        return "search.html";
    }
    @GetMapping("/search/{nation}/{genre}")
    public String searchNationGenre(@PathVariable("nation") String nation, @PathVariable("genre") String genre, Model model, String keyword, String classification) {
        List<Book> books = null;
        if (keyword == null) {
            books = bookService.searchNationGenre(nation, genre);
        }
        else {
            books = bookService.searchNationGenreKeyword(nation, genre, keyword, classification);
        }
        String cnt = bookService.stringChange(nation) + " / ";
        String res = cnt.concat(genre);
        model.addAttribute("nara", nation);
        model.addAttribute("books", books);
        model.addAttribute("category", res);
        model.addAttribute("genre", genre);
        return "search.html";
    }
}
