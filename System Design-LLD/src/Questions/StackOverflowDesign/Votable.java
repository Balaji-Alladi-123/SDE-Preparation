package Questions.StackOverflowDesign;

public interface Votable {
    void vote(User voter, VoteType voteType);
    int getVoteCount();
}
