package com.example.mobileapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {

    EditText weightText;
    EditText heightText;
    Button calculateButton;
    TextView yourBIM;
    TextView yourResult;
    TextView yourResultnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
    }

    public void CalculateBMI(View view) {

        double weight = 0;
        double height = 0;
        double bmi = 0;
        String msg = "";

        weightText = (EditText) findViewById(R.id.weight);
        heightText = (EditText) findViewById(R.id.height);

        calculateButton = (Button) findViewById(R.id.calc);

        yourBIM = (TextView) findViewById(R.id.your_bim);
        yourResultnr = (TextView) findViewById(R.id.resultnr);

        yourResult = (TextView) findViewById(R.id.result);

        weight = Double.parseDouble(weightText.getText().toString());
        height = Double.parseDouble(heightText.getText().toString());

        bmi = height * height;
        bmi = weight * 10000 / bmi;

        yourResultnr.setText(String.valueOf(String.format("%.2f", bmi)));

        if (bmi < 18.5) {
            msg = "UnderWeight";
        } else if (bmi > 18.5 && bmi < 24.9) {
            msg = "Normal Weight";
        } else if (bmi > 25 && bmi < 29.9) {
            msg = "OverWeight";
        }
        else if (bmi>30){
            msg = "Obesity";
        }

        yourResult.setText(msg);

    }
}
