package com.quiz_mongodb.dao;

import java.util.ArrayList;

public interface IDAO<T> {
    ArrayList<T> getAll();
    void add(T value);
}
