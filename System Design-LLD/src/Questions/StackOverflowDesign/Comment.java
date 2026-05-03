package Questions.StackOverflowDesign;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Comment {
    private final UUID id;
    private final String content;
    private final User author;
    private  LocalDateTime creationDate;

    public Comment(User author,String content) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.author = author;
        this.creationDate = LocalDateTime.now();
    }

    public UUID getId() {
        return UUID.randomUUID();
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
