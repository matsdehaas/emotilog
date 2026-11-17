package com.example.emotilog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Emotion {
    private String name;
    private String emoji;
    private String timestamp;

    public Emotion(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public String getTimestamp() { return timestamp; }
}
