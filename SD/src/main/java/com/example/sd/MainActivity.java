package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText e1 = (EditText) findViewById(R.id.editText);
        final EditText e2 = (EditText) findViewById(R.id.editText2);
        Button b1 = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);

        String path = getPreferences(MODE_PRIVATE).getString("fpath", "/sdcard/file2");
        e1.setText(path);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                File f = new File(e1.getText().toString());
                String s = "";
                StringBuilder sb = new StringBuilder();
                FileReader fr = null;

                try {
                    fr = new FileReader(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: File not found!", Toast.LENGTH_SHORT).show();
                    return; // Stop execution if file not found
                }

                // This check prevents a crash if fr is null
                if (fr != null) {
                    BufferedReader br = new BufferedReader(fr);
                    try {
                        while ((s = br.readLine()) != null) {
                            sb.append(s + "\n");
                        }
                        // Close the reader
                        br.close();
                        Toast.makeText(getApplicationContext(), "File Read Successfully !!!", Toast.LENGTH_LONG).show();
                        e2.setText(sb);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                File f = new File(e1.getText().toString());
                FileWriter fw = null;
                try {
                    fw = new FileWriter(f);
                    // Corrected: Was e2..toString()
                    fw.write(e2.getText().toString());
                    fw.close(); // Close the writer successfully

                    SharedPreferences.Editor e = getPreferences(MODE_PRIVATE).edit();
                    e.putString("fpath", f.getPath());
                    e.commit();
                    Toast.makeText(getApplicationContext(), "Saved Successfully !!!", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error saving file!", Toast.LENGTH_SHORT).show();
                } finally {
                    // Ensure the FileWriter is closed even if an error occurs
                    if (fw != null) {
                        try {
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}