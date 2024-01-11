module com.quiz_mongodb {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.quiz_mongodb to javafx.fxml;
    exports com.quiz_mongodb;
    exports com.quiz_mongodb.controller;
}