package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Patterns;
import com.google.android.material.textfield.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView register;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        DB = new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Validation

                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if (Email.equals("") || Password.equals(""))
                    Toast.makeText(LoginActivity.this, getString(R.string.user_not_found), Toast.LENGTH_LONG).show();
                else {
                    Boolean checkuserPassword = DB.checkemailPassword(Email, Password);
                    if (checkuserPassword == true) {
                        Toast.makeText(LoginActivity.this, "Succesfully", Toast.LENGTH_LONG).show();
                        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(mainActivityIntent);

                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.wrong_credentials), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}