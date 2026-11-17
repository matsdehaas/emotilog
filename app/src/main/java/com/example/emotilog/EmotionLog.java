package com.example.emotilog;

import java.util.ArrayList;
import java.util.List;

public class EmotionLog {
    private List<Emotion> entries = new ArrayList<>();

    public void add(Emotion e) {
        entries.add(e);
    }

    public List<Emotion> getEntries() {
        return entries;
    }
}
