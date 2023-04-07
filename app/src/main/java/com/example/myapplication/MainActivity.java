// Самодельный таймер как-то так
package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timeText;
    private Button startButton, timerButton;
    private Handler handler;
    private boolean isStopped; 
    private boolean isForward = true; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.time_text);
        startButton = findViewById(R.id.start_button);
        timerButton = findViewById(R.id.timer_button);

        // Инициализируем Handler
        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isStopped) {
                    startTimer();
                    startButton.setText("Stop");
                } else { 
                    stopTimer();
                    startButton.setText("Start");
                }
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                isForward = !isForward;
            }
        });
    }

    private void startTimer() {
        isStopped = true; 
        handler.post(new Runnable() {
            int seconds = 0;
            int minutes = 0;
            int hours = 0;

            @Override
            public void run() {
                if (isForward) { 
                    seconds++;
                    if (seconds == 60) {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes == 60) {
                        minutes = 0;
                        hours++;
                    }
                } else { 
                    seconds--;
                    if (seconds == -1) {
                        seconds = 59;
                        minutes--;
                    }
                    if (minutes == -1) {
                        minutes = 59;
                        hours--;
                    }
                }
             
                timeText.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void stopTimer() {
        isStopped = false; 
        handler.removeCallbacksAndMessages(null); 
    }
}
