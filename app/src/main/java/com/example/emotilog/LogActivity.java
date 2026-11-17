package com.example.emotilog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogActivity extends AppCompatActivity {

    private EditText dateInput;
    private TextView logText;
    private TextView summaryText;
    private Button loadButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        dateInput = findViewById(R.id.date_input);
        logText = findViewById(R.id.log_text);
        summaryText = findViewById(R.id.summary_text);
        loadButton = findViewById(R.id.load_button);
        backButton = findViewById(R.id.back_button);

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        dateInput.setText(today);

        loadLogsForDate(today);

        loadButton.setOnClickListener(v -> loadLogsForDate(dateInput.getText().toString()));

        backButton.setOnClickListener(v -> finish());
    }

    private void loadLogsForDate(String date) {
        StringBuilder logBuilder = new StringBuilder();
        StringBuilder summaryBuilder = new StringBuilder();

        int[] counts = new int[MainActivity.emotions.size()];
        int total = 0;

        for (Emotion e : User.getInstance().getLog().getEntries()) {
            String logDate = e.getTimestamp().split(" ")[0]; // yyyy-mm-dd
            if (logDate.equals(date)) {
                logBuilder.append(e.getTimestamp())
                        .append(" — ")
                        .append(e.getEmoji())
                        .append(" ")
                        .append(e.getName())
                        .append("\n");

                for (int i = 0; i < MainActivity.emotions.size(); i++) {
                    if (MainActivity.emotions.get(i).getName().equals(e.getName())) {
                        counts[i]++;
                        break;
                    }
                }
                total++;
            }
        }

        logText.setText(logBuilder.toString());

        summaryBuilder.append("Summary for ").append(date).append(":\n");
        if (total == 0) {
            summaryBuilder.append("No logs found.\n");
        } else {
            for (int i = 0; i < MainActivity.emotions.size(); i++) {
                if (counts[i] > 0) {
                    Emotion emo = MainActivity.emotions.get(i);
                    summaryBuilder.append(emo.getEmoji())
                            .append(" ")
                            .append(emo.getName())
                            .append(": ")
                            .append(counts[i])
                            .append("\n");
                }
            }
        }

        summaryText.setText(summaryBuilder.toString());
    }
}
