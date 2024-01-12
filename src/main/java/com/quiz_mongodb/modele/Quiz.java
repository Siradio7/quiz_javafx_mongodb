package com.quiz_mongodb.modele;

public class Quiz {
    private String id;
    private String title;
    private String description;

    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Quiz(String id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + "_" + title + "_" + description;
    }
}
