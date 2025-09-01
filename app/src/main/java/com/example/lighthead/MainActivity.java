package com.example.lighthead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout bgElement = findViewById(R.id.activity_main);
        bgElement.setBackgroundColor(Color.WHITE);

        myButtonListenerMethod();
    }

    public void myButtonListenerMethod() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout bgElement = findViewById(R.id.activity_main);
                int color = ((ColorDrawable) bgElement.getBackground()).getColor();

                if (color == Color.RED) {
                    bgElement.setBackgroundColor(Color.BLUE);
                    button.setText("BLUE");
                } else {
                    bgElement.setBackgroundColor(Color.RED);
                    button.setText("RED");
                }
            }
        });
    }
}
