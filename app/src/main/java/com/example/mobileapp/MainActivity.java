package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tomer.fadingtextview.FadingTextView;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button register;
    FadingTextView fadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        fadingTextView = (FadingTextView) findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                for (int i=0; i < 2; i++) {
                    Toast.makeText(this, "Click login and start exercising", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.item2:
                for (int i=0; i < 2; i++) {
                    Toast.makeText(this, "You've got fitness tricks, reminder, qrcode, stopwatch and music", Toast.LENGTH_LONG).show();
                }                return true;
            case R.id.item3:
                for (int i=0; i < 2; i++) {
                    Toast.makeText(this, "If you forgot your password, you can reset it on the login page", Toast.LENGTH_LONG).show();
                }                return true;
            default:return super.onOptionsItemSelected(item);


        }
    }
}