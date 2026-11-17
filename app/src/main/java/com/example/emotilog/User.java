package com.example.emotilog;

public class User {
    private static User instance;
    private EmotionLog log;

    private User() {
        log = new EmotionLog();
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public EmotionLog getLog() {
        return log;
    }
}
