package com.kalyani.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    Button btnStart, btnPause, btnReset;

    Handler handler = new Handler();

    int seconds = 0;
    int milliseconds = 0;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timerText);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(v -> running = true);

        btnPause.setOnClickListener(v -> running = false);

        btnReset.setOnClickListener(v -> {
            running = false;
            seconds = 0;
            milliseconds = 0;
            timerText.setText("00:00:00");
        });

        runTimer();
    }

    public void runTimer() {

        handler.post(new Runnable() {
            @Override
            public void run() {

                if (running) {

                    milliseconds++;

                    if (milliseconds == 100) {
                        milliseconds = 0;
                        seconds++;
                    }

                    int minutes = seconds / 60;
                    int secs = seconds % 60;

                    String time = String.format(
                            java.util.Locale.getDefault(),
                            "%02d:%02d:%02d",
                            minutes,
                            secs,
                            milliseconds
                    );

                    timerText.setText(time);
                }

                handler.postDelayed(this, 10);
            }
        });
    }
}