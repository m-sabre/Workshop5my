package pl.coderslab;

import org.springframework.stereotype.Component;

import pl.coderslab.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemoryBookService {

    private static Long nextId = 4L;
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thiniking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa Java.", "Sierra Kathy, Bates Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion",
                "programming"));
    }

    public void addBook(Book book) {

        book.setId(nextId);
        nextId++;
        list.add(book);
    }

    public Book showById(Long id) {
        List<Book> oneBook = list.stream()
                .filter(book -> book.getId().equals(id))
                .collect(Collectors.toList());
        return oneBook.get(0);
    }

    public List<Book> showBooks() {
        return list;
    }


    public void deleteBook(Long id) {
        list = list.stream()
                .filter(book -> !book.getId().equals(id))
                .collect(Collectors.toList());
    }

    public void updateBook(Book updatedBook) {
        list = list.stream()
                .peek(book -> {
                    if (book.getId().equals(updatedBook.getId())) {
                        book.setTitle(updatedBook.getTitle());
                        book.setType(updatedBook.getType());
                        book.setPublisher(updatedBook.getPublisher());
                        book.setIsbn(updatedBook.getIsbn());
                        book.setAuthor(updatedBook.getAuthor());
                    }

                })
                .collect(Collectors.toList());
    }


}