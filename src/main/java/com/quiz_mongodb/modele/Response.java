package com.quiz_mongodb.modele;

public class Response {
    private String text;
    private boolean isTrueAnswer;

    public Response(String text, boolean isTrueAnswer) {
        this.text = text;
        this.isTrueAnswer = isTrueAnswer;
    }

    public String getText() {
        return text;
    }

    public boolean isTrueAnswer() {
        return isTrueAnswer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void isTrueAnswer(boolean value) {
        this.isTrueAnswer = value;
    }
}
