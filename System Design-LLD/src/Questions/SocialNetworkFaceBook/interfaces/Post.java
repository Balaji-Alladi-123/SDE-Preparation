package Questions.SocialNetworkFaceBook.interfaces;

import Questions.SocialNetworkFaceBook.models.Comment;
import Questions.SocialNetworkFaceBook.models.Like;
import Questions.SocialNetworkFaceBook.models.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface Post {
    UUID getId();

    User getAuthor();

    String getContent();

    Timestamp getTimestamp();

    void addLike(User user);

    void addComment(User user, String text);

    long getLikeCount();

    List<Like> getLikes();

    List<Comment> getComments();
}
