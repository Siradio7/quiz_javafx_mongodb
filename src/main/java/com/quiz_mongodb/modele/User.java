package com.quiz_mongodb.modele;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String school;
    private int numberOfGame;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email, String school, int numberOfGame) {
        this(username, password);
        this.email = email;
        this.school = school;
        this.numberOfGame = numberOfGame;
    }

    public User(String id, String username, String password, String email, String school, int numberOfGame) {
        this(username, password, email, school, numberOfGame);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public int getNumberOfGame() {
        return numberOfGame;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return this.id + "_" + this.username + "_" + this.password;
    }
}
