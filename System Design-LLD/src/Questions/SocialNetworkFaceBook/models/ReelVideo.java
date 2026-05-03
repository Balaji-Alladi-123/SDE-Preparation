package Questions.SocialNetworkFaceBook.models;

import Questions.SocialNetworkFaceBook.interfaces.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReelVideo implements Post {
    private final UUID id;
    private final User author;
    private final String content;
    private final Timestamp timestamp;
    private final Map<UUID, Like> userIdLikeMap;
    private final List<Comment> comments;

    public ReelVideo(User author, String content) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.content = content;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.userIdLikeMap = new ConcurrentHashMap<>();
        this.comments = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void addLike(User user) {
        if (!userIdLikeMap.containsKey(user.getId())) {
            userIdLikeMap.put(user.getId(), new Like(user));
        } else {
            userIdLikeMap.remove(user);
        }
    }

    public synchronized void addComment(User user, String text) {
        comments.add(new Comment(user, text));
    }

    public long getLikeCount() {
        return userIdLikeMap.size();
    }

    public List<Like> getLikes() {
        return userIdLikeMap.values().stream().toList();
    }

    public UUID getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Map<UUID, Like> getUserIdLikeMap() {
        return userIdLikeMap;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
