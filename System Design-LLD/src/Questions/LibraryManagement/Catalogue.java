package Questions.LibraryManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogue {
    private final Map<String, List<BookCopy>> byTitle = new HashMap<>();
    private final Map<String, List<BookCopy>> byAuthor = new HashMap<>();
    private final Map<String, List<BookCopy>> byAudition = new HashMap<>();


    public synchronized void addBook(BookCopy bookCopy) {
        Book book = bookCopy.getBook();
        byTitle.computeIfAbsent(book.name, k -> new ArrayList<>()).add(bookCopy);
        byAudition.computeIfAbsent(book.audition, k -> new ArrayList<>()).add(bookCopy);
        byAuthor.computeIfAbsent(book.author, k -> new ArrayList<>()).add(bookCopy);
    }

    public synchronized List<Book> getBooksByTitle(String title) {
        return byTitle.get(title).stream()
                .map(bookCopy -> bookCopy.getBook())
                .distinct()
                .toList();
    }

    public synchronized List<Book> getBooksByAudition(String audition) {
        return byAudition.get(audition).stream()
                .map(bookCopy -> bookCopy.getBook())
                .distinct()
                .toList();
    }

    public synchronized List<BookCopy> getBookCopiesByTitle(String title) {
        return byTitle.getOrDefault(title, List.of());
    }

    public synchronized List<Book> getBooksByAuthor(String author) {
        return byAudition.get(author).stream()
                .map(bookCopy -> bookCopy.getBook())
                .distinct()
                .toList();
    }

    public Map<String, List<BookCopy>> getByAudition() {
        return byAudition;
    }
}
