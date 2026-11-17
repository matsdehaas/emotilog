package com.example.emotilog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout buttonsContainer;
    public static List<Emotion> emotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsContainer = findViewById(R.id.buttons_container);

        emotions = new ArrayList<>();
        emotions.add(new Emotion("happy", "😊"));
        emotions.add(new Emotion("sad", "😢"));
        emotions.add(new Emotion("angry", "😡"));
        emotions.add(new Emotion("surprised", "😲"));
        emotions.add(new Emotion("love", "😍"));
        emotions.add(new Emotion("fear", "😱"));
        emotions.add(new Emotion("tired", "😴"));
        emotions.add(new Emotion("confused", "😕"));

        for (Emotion e : emotions) {
            Button btn = buildEmotionButton(e);
            buttonsContainer.addView(btn);
        }

        Button openLogBtn = findViewById(R.id.open_log_button);
        openLogBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LogActivity.class);
            startActivity(intent);
        });
    }

    private Button buildEmotionButton(final Emotion e) {
        Button b = new Button(this);
        b.setText(e.getEmoji());
        b.setContentDescription(e.getName());

        GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
        lp.width = 0;
        lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        b.setLayoutParams(lp);

        b.setOnClickListener(v -> {
            User.getInstance().getLog().add(e);
            Toast.makeText(this, "Logged: " + e.getName(), Toast.LENGTH_SHORT).show();
        });

        return b;
    }
}
