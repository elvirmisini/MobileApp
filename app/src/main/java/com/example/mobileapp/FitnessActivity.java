    package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

    public class FitnessActivity extends AppCompatActivity {
    Button start;
    Button logout1;
    TextView StafBtn;
    ImageView gymAchievement;
    ImageView gymFood;
    ImageView gymMode;
    ImageView gymTime;
    RelativeLayout mainLayout;
    TextView Location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        start = (Button) findViewById(R.id.start);
        logout1 = (Button) findViewById(R.id.logout1);
        gymAchievement = (ImageView) findViewById(R.id.gymAchievement);
        gymFood = (ImageView) findViewById(R.id.gymFood);
        gymMode = (ImageView) findViewById(R.id.gymMode);
        gymTime = (ImageView) findViewById(R.id.gymTime);
        mainLayout=findViewById(R.id.mainLayout);
        Location=(TextView) findViewById(R.id.Location);
        StafBtn=(TextView) findViewById(R.id.StafBtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                startActivity(intent);
            }
        });

        StafBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Staff.class);
                startActivity(intent);
                finish();
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(mainLayout,"Are you sure you want to log out?",Snackbar.LENGTH_LONG).setAction("LogOut", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
            }
        });

        gymAchievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), AsyncTheTask.class);
                startActivity(intent);
            }
        });

        gymFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), BmiActivity.class);
                startActivity(intent);
            }
        });

        gymMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Wrokouts.class);
                startActivity(intent);
            }
        });

        gymTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                startActivity(intent);
            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            if (bundle.getString("some")!=null){
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();

            }
        }
    }



}