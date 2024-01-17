module com.quiz_mongodb {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.slf4j;

    requires java.prefs;

    opens com.quiz_mongodb to javafx.fxml;
    exports com.quiz_mongodb;
    opens com.quiz_mongodb.controller to javafx.fxml;
    exports com.quiz_mongodb.controller;
    exports com.quiz_mongodb.database;
    opens com.quiz_mongodb.database to javafx.fxml;
    exports com.quiz_mongodb.enums;
    opens com.quiz_mongodb.enums to javafx.fxml;
}