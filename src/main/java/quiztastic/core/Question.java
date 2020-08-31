package quiztastic.core;

/**
 * The Question Class.
 *
 * Should contain information about the questions
 */
public class Question {

    private final int id;
    private final int score;
    private final Category category;
    private final String question;
    private final String answer;

    public Question(int id, int score, Category category, String question, String answer) {
        this.id = id;
        this.score = score;
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Category getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " Score: " + score +
                " Category: " + category +
                " Question: " + question +
                " Answer: " + answer
                ;
    }
}
