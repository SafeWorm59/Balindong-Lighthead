package com.example.lighthead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    Button changeBtn;
    Button startBtn;
    Handler handler = new Handler();
    boolean autoMode = false;

    // 0 = RED, 1 = YELLOW, 2 = GREEN, 3 = YELLOW AGAIN
    int lightState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout bg = findViewById(R.id.activity_main);
        bg.setBackgroundColor(Color.WHITE); // Start with WHITE background

        changeBtn = findViewById(R.id.button);
        startBtn = findViewById(R.id.startbutton);

        changeBtn.setText("Change Me"); // Default button text

        // Manual change button
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLight();
            }
        });

        // Start/Stop button
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!autoMode) {
                    autoMode = true;
                    startBtn.setText("STOP");
                    lightState = 0; // reset to RED cycle start
                    changeAutomatically();
                } else {
                    autoMode = false;
                    startBtn.setText("START");
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    private void changeLight() {
        RelativeLayout bg = findViewById(R.id.activity_main);

        if (lightState == 0) { // RED
            bg.setBackgroundColor(Color.RED);
            changeBtn.setText("RED");
            lightState = 1;
        } else if (lightState == 1) { // YELLOW
            bg.setBackgroundColor(Color.YELLOW);
            changeBtn.setText("YELLOW");
            lightState = 2;
        } else if (lightState == 2) { // GREEN
            bg.setBackgroundColor(Color.GREEN);
            changeBtn.setText("GREEN");
            lightState = 3;
        } else { // YELLOW AGAIN → RED
            bg.setBackgroundColor(Color.YELLOW);
            changeBtn.setText("YELLOW");
            lightState = 0; // back to RED next
        }
    }

    private void changeAutomatically() {
        if (!autoMode) return;

        changeLight();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeAutomatically();
            }
        }, 1000); // 1 second each step
    }
}
