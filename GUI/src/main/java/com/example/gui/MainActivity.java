package com.example.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name, spin_val, branch;
    String[] gender = {"male", "female"};
    float val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText e1 = (EditText) findViewById(R.id.PersonName);
        final Spinner sp = (Spinner) findViewById(R.id.spinner);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        final RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
        final RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        final Button b = (Button) findViewById(R.id.button);

        sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spin_val = gender[i];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // Do nothing
                    }
                }
        );

        ArrayAdapter<String> spin_Adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, gender);
        sp.setAdapter(spin_Adapter);

        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rb1.isChecked() == true)
                            branch = "CSE";
                        if (rb2.isChecked() == true)
                            branch = "IT";
                        if (rb3.isChecked() == true)
                            branch = "ECE";
                    }
                }
        );

        rb.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        val = v;
                    }
                }
        );

        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        name = e1.getText().toString();
                        Toast.makeText(getApplicationContext(), "Name:" + name + "\n Gender:" + spin_val + "\n Degree:" + branch + "\n GK Level:" + val, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}