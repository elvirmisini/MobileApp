package com.example.mobileapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class BarcodeScanningActivity extends AppCompatActivity implements View.OnClickListener {

    Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanning);

        scan=findViewById(R.id.Scan);

        scan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){
        IntentIntegrator intentIntegrator=new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(Scan.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt(String.valueOf(R.string.scanning));
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {

        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestcode,resultcode,data);
        if (intentResult != null){
            if (intentResult.getContents()!=null){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage(intentResult.getContents());
                builder.setTitle("Scanning result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), FitnessActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
            else {
                Toast.makeText(this,"No results",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestcode,resultcode,data);
        }
    }
}