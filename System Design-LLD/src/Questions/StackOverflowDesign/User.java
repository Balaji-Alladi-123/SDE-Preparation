package Questions.StackOverflowDesign;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class User {
    private final UUID userId;
    private final String UserName;
    private final String email;
    private final String PhoneNumber;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    User(UUID userId, String userName, String email, String phoneNumber, int reputation, List<Question> questions, List<Answer> answers, List<Comment> comments) {
        this.userId = userId;
        UserName = userName;
        this.email = email;
        PhoneNumber = phoneNumber;
        this.reputation = reputation;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Question askQuestion(String title, String content, List<String> tags) {
        Question question = new Question(this, title, content, tags);
        questions.add(question);
        updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Answer askAnswer(Question question, String content) {
        Answer answer = new Answer(this, question, content);
        answers.add(answer);
        updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public synchronized void updateReputation(int marks) {
        this.reputation += marks;
    }

    // Getters
    public UUID getId() { return userId; }
    public String getUsername() { return UserName; }
    public int getReputation() { return reputation; }
    public List<Question> getQuestions() { return new ArrayList<>(questions); }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Comment> getComments() { return new ArrayList<>(comments); }
}
