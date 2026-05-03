package Questions.LibraryManagement;

import java.util.UUID;

public class BookCopy {
    private final UUID id;
    private final Book book;
    private boolean isAvailable;

    BookCopy(Book book) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.isAvailable = true;
    }

    public Book getBook() {
        return book;
    }

    public synchronized void markIssued() {
        if (!isAvailable) throw new IllegalStateException("mark issued");
        isAvailable = false;
    }

    public synchronized void markReturned() {
        isAvailable = true;
    }

    public synchronized boolean isAvailable() {
        return isAvailable;
    }
}
