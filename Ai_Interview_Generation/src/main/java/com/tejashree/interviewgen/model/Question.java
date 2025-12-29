package com.tejashree.interviewgen.model;

public class Question {

    private String text;          // actual interview question
    private String type;          // technical / behavioral / scenario
    private String modelAnswer;   // generated answer
    private String keywords;

    // Default constructor
    public Question() {}

    // Parameterized constructor
    public Question(String text, String type, String modelAnswer, String keywords) {
        this.text = text;
        this.type = type;
        this.modelAnswer = modelAnswer;
        this.keywords = keywords;
    }

    // Getter & Setter for question text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter & Setter for question type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter & Setter for AI-generated answer
    public String getModelAnswer() {
        return modelAnswer;
    }

    public void setModelAnswer(String modelAnswer) {
        this.modelAnswer = modelAnswer;
    }

    // Getter & Setter for keywords
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}