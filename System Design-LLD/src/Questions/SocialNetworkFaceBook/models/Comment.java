package Questions.SocialNetworkFaceBook.models;

import java.sql.Timestamp;
import java.util.UUID;

public class Comment {
    private final  UUID id;
    private final User user;
    private final String text;
    private final Timestamp timestamp;

    public Comment(User user, String text) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.text = text;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
