package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText reg, name, marks;
    Button addb, vb, vab, delb, ub;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = findViewById(R.id.editText);
        name = findViewById(R.id.editText2);
        marks = findViewById(R.id.editText3);
        addb = findViewById(R.id.button);
        vb = findViewById(R.id.button2);
        vab = findViewById(R.id.button3);
        delb = findViewById(R.id.button4);
        ub = findViewById(R.id.button5);

        db = openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(RegNo VARCHAR,Name VARCHAR,Mark VARCHAR);");

        // Corrected: new View.OnClickListener()
        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (reg.getText().toString().trim().length() == 0 ||
                        name.getText().toString().trim().length() == 0 ||
                        marks.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('" + reg.getText() + "','" + name.getText() + "','" + marks.getText() + "');");
                showMessage("Success", "Record added");
                clearText();
            }
        });

        delb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (reg.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Reg. No.");
                    return;
                }
                // Corrected: Added space between student and WHERE
                Cursor c = db.rawQuery("SELECT * FROM student WHERE RegNo='" + reg.getText() + "'", null);
                if (c.moveToFirst()) {
                    // Corrected: Added space between DELETE and FROM
                    db.execSQL("DELETE FROM student WHERE RegNo='" + reg.getText() + "'");
                    showMessage("Success", "Record Deleted");
                } else {
                    showMessage("Error", "Invalid Reg. No.");
                }
                clearText();
            }
        });

        ub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (reg.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Reg. No.");
                    return;
                }
                // Corrected: Added space between student and WHERE
                Cursor c = db.rawQuery("SELECT * FROM student WHERE RegNo='" + reg.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("UPDATE student SET Name='" + name.getText() + "',Mark='" + marks.getText() + "' WHERE RegNo='" + reg.getText() + "'");
                    showMessage("Success", "Record Modified");
                } else {
                    showMessage("Error", "Invalid Reg. No.");
                }
                clearText();
            }
        });

        vb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (reg.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Reg. No.");
                    return;
                }
                // Corrected: Added space between student and WHERE
                Cursor c = db.rawQuery("SELECT * FROM student WHERE RegNo='" + reg.getText() + "'", null);
                if (c.moveToFirst()) {
                    name.setText(c.getString(1));
                    marks.setText(c.getString(2));
                } else {
                    showMessage("Error", "Invalid Reg. No.");
                    clearText();
                }
            }
        });

        vab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor c = db.rawQuery("SELECT * FROM student", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Reg. No : " + c.getString(0) + "\n");
                    buffer.append("Name : " + c.getString(1) + "\n");
                    // Corrected: Changed "\N\n" to "\n\n"
                    buffer.append("Mark : " + c.getString(2) + "\n\n");
                }
                showMessage("Student Details", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        reg.setText("");
        name.setText("");
        marks.setText("");
        reg.requestFocus();
    }
}