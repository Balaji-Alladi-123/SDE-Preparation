package Questions.StackOverflowDesign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Question implements Commentable, Votable{
    private final UUID id;
    private final String title;
    private final String content;
    private final User author;
    private final Date creationDate;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Vote> votes;
    private Answer acceptedAnswer;

    public Question(User author, String title, String content, List<String> tagNames) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.title = title;
        this.content = content;
        this.creationDate = new Date();
        this.answers = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();
        for (String tagName : tagNames) {
            this.tags.add(new Tag(tagName));
        }
    }

    public synchronized void addAnswer(Answer answer) {
        if (!answers.contains(answer)) {
            answers.add(answer);
        }
    }

    public synchronized void acceptAnswer(Answer answer) {
        this.acceptedAnswer = answer;
        answer.markAsAccepted();
    }

    @Override
    public void vote(User voter, VoteType type) {
        votes.removeIf(v -> v.getUser().equals(voter));
        votes.add(new Vote(voter, type));
        author.updateReputation(5 * (type == VoteType.UP_VOTE ? 1 : -1));  // +5 for upvote, -5 for downvote
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

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    // Getters
    public UUID getId() { return id; }
    public User getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Tag> getTags() { return new ArrayList<>(tags); }
    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }
}
