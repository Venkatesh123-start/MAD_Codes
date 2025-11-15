package com.example.event;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float font = 20;
    int count = 1;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView t1 = (TextView) findViewById(R.id.textView2);
        t1.setTextSize(15);

        b1 = (Button) findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.setTextSize(font);
                font = font + 5;
                if (font == 50)
                    font = 20;
            }
        });

        b2 = (Button) findViewById(R.id.button5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (count) {
                    case 1:
                        t1.setTextColor(Color.parseColor("#7f00ff"));
                        break;
                    case 2:
                        t1.setTextColor(Color.parseColor("#00FF00"));
                        break;
                    case 3:
                        t1.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    case 4:
                        t1.setTextColor(Color.parseColor("#0000FF"));
                        break;
                }
                count++;
                if (count == 5)
                    count = 1;
            }
        });

        b3 = (Button) findViewById(R.id.button6);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (count) {
                    case 1:
                        t1.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
                        break;
                    case 2:
                        t1.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
                        break;
                    case 3:
                        t1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        break;
                    case 4:
                        t1.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
                        break;
                }
                count++;
                if (count == 5)
                    count = 1;
            }
        });

    }
}