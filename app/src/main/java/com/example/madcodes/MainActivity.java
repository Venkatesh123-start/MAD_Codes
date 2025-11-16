package com.example.madcodes;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Example: Set text programmatically
        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome to Mobile Application Development!");
    }
}
