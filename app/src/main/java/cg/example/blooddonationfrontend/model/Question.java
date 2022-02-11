package cg.example.blooddonationfrontend.model;

import java.util.UUID;

public class Question {

    private UUID id;
    private String questionBody;
    private int questionOrder;
    private Enums.AnswerType answerType;
    private Enums.Sex genderSpecific;
    private Boolean goodAnswerNo;

    public Question() {
    }

    public Question(String questionBody, int questionOrder, Enums.AnswerType answerType, Enums.Sex genderSpecificQuestion, boolean isGoodAnswerNo) {
        this.questionBody = questionBody;
        this.questionOrder = questionOrder;
        this.answerType = answerType;
        this.genderSpecific = genderSpecificQuestion; // if null/empty -> used for both
        this.goodAnswerNo = isGoodAnswerNo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    public Enums.AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(Enums.AnswerType answerType) {
        this.answerType = answerType;
    }

    public Enums.Sex getGenderSpecific() {
        return genderSpecific;
    }

    public void setGenderSpecific(Enums.Sex genderSpecific) {
        this.genderSpecific = genderSpecific;
    }

    public Boolean isGoodAnswerNo() {
        return goodAnswerNo;
    }

    public void setGoodAnswerNo(Boolean goodAnswerNo) {
        goodAnswerNo = goodAnswerNo;
    }
}
