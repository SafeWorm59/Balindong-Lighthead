package com.example.lighthead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class MainActivity extends AppCompatActivity {
    Button changeBtn;
    Button startBtn;
    Handler handler = new Handler();
    boolean autoMode = false; // to check if auto is running

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout bg = findViewById(R.id.activity_main);
        bg.setBackgroundColor(Color.WHITE); // default color red

        changeBtn = findViewById(R.id.button);
        startBtn = findViewById(R.id.startbutton);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout bg = findViewById(R.id.activity_main);
                int colorNow = ((ColorDrawable) bg.getBackground()).getColor();

                if (colorNow == Color.RED) {
                    bg.setBackgroundColor(Color.YELLOW);
                    changeBtn.setText("YELLOW");
                } else if (colorNow == Color.YELLOW) {
                    bg.setBackgroundColor(Color.GREEN);
                    changeBtn.setText("GREEN");
                } else {
                    bg.setBackgroundColor(Color.RED);
                    changeBtn.setText("RED");
                }
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoMode == false) {
                    autoMode = true;
                    startBtn.setText("STOP");
                    changeAutomatically();
                } else {
                    autoMode = false;
                    startBtn.setText("START");
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    private void changeAutomatically() {
        if (autoMode == false) {
            return;
        }

        RelativeLayout bg = findViewById(R.id.activity_main);
        int colorNow = ((ColorDrawable) bg.getBackground()).getColor();

        if (colorNow == Color.RED) {
            bg.setBackgroundColor(Color.YELLOW);
            changeBtn.setText("YELLOW");
        } else if (colorNow == Color.YELLOW) {
            bg.setBackgroundColor(Color.GREEN);
            changeBtn.setText("GREEN");
        } else {
            bg.setBackgroundColor(Color.RED);
            changeBtn.setText("RED");
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeAutomatically();
            }
        }, 1000);
    }
}
