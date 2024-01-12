package com.quiz_mongodb.modele;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String id;
    private String idQuiz;
    private String text;
    private List<Response> responses = new ArrayList<>();

    public Question(String idQuiz, String text, List<Response> responses) {
        this.idQuiz = idQuiz;
        this.text = text;
        this.responses = responses;
    }

    public Question(String id, String idQuiz, String text, List<Response> responses) {
        this(idQuiz, text, responses);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getIdQuiz() {
        return idQuiz;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setIdQuiz(String idQuiz) {
        this.idQuiz = idQuiz;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
