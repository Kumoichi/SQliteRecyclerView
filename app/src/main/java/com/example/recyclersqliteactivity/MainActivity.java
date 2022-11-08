package com.example.recyclersqliteactivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, email, age;
    Button insert, view, delete, timer_view;
    TextView textView;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("timedata", 0);
        String studyTime = String.valueOf(intValue);
        textView = findViewById(R.id.time_data);
        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        timer_view = findViewById(R.id.timer_view);
        delete = findViewById(R.id.btnDelete);
        textView.setText(studyTime);

        DB = new DBHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Userlist.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String ageTXT = age.getText().toString();

                Boolean checkinsertdata  = DB.insertuserdata(nameTXT, emailTXT, studyTime);
                if(checkinsertdata==true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata  = DB.deleteuserdata(nameTXT);
                if(checkdeletedata==true)
                {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        timer_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Timer.class));
            }
        });

    }
}