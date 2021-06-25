package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkoutProgram extends AppCompatActivity {

    TextView fourday,women_workout,home,musclegrowth,totalBody,program_text;
    Button back;
    private StringBuilder text = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_program);

        fourday=(TextView) findViewById(R.id.fourday);
        women_workout=(TextView) findViewById(R.id.women_workout);
        home=(TextView) findViewById(R.id.home);
        musclegrowth=(TextView) findViewById(R.id.musclegrowth);
        totalBody=(TextView) findViewById(R.id.totalBody);
        program_text=(TextView) findViewById(R.id.program_text);
        back=(Button) findViewById(R.id.back);

        fourday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.delete(0, text.length());
                ReadFile("4day.txt");
            }
        });

        women_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.delete(0, text.length());
                ReadFile("women.txt");
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.delete(0, text.length());
                ReadFile("home.txt");
            }
        });

        musclegrowth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.delete(0, text.length());
                ReadFile("muscle.txt");
            }
        });

        totalBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.delete(0, text.length());
                ReadFile("total.txt");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FitnessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void ReadFile(String file){

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(file)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            program_text.setText((CharSequence) text);
        }
    }
}