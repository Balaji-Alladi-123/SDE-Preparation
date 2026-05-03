package Questions.StackOverflowDesign;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Answer implements Commentable, Votable {
    private final UUID id;
    private final String content;
    private final User author;
    private final Question question;
    private boolean isAccepted;
    private final LocalDateTime creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(User author, Question question, String content) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.question = question;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.isAccepted = false;
    }

    @Override
    public void vote(User voter, VoteType type) {
        votes.removeIf(v -> v.getUser().equals(voter));
        votes.add(new Vote(voter, type));
        author.updateReputation(10 * (type == VoteType.UP_VOTE ? 1 : -1));  // +10 for upvote, -10 for downvote
    }

    @Override
    public int getVoteCount() {
        return votes.stream()
                .mapToInt(v -> v.getVoteType() == VoteType.UP_VOTE ? 1 : -1)
                .sum();
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void markAsAccepted() {
        if (isAccepted) {
            throw new IllegalStateException("This answer is already accepted");
        }
        isAccepted = true;
        author.updateReputation(15);  // +15 reputation for accepted answer
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    // Getters
    public UUID getId() { return id; }
    public User getAuthor() { return author; }
    public Question getQuestion() { return question; }
    public String getContent() { return content; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public boolean isAccepted() { return isAccepted; }
}
