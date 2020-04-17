package me.kisoft.covid19.models;


public class Question {
    private Long id;
    private String question;
    private boolean answered;
    private QuestionType type;

    public Question() {
    }

    public Question(Long id, String question, QuestionType type) {
        this.id = id;
        this.question = question;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answered=" + answered +
                ", type=" + type +
                '}';
    }
}
