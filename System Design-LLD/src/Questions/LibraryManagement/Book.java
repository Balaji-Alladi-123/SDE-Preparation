package Questions.LibraryManagement;

import java.util.Date;
import java.util.UUID;

public class Book {
    UUID bookId;
    String name;
    String author;
    String audition;
    Date publishedDate;

    Book(String name, String author, String audition, Date publishedDate) {
        this.bookId = UUID.randomUUID();
        this.name = name;
        this.audition = audition;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getAudition() {
        return audition;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getName() {
        return name;
    }
}
