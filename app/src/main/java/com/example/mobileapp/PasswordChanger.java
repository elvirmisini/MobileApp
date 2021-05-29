package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import com.google.android.material.textfield.TextInputLayout;

public class PasswordChanger extends AppCompatActivity {


    EditText email, Oldpassword,Newpassword;
    Button change;
    TextView backtoLogin;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_changer);


        email = (EditText) findViewById(R.id.email);
        Oldpassword = (EditText) findViewById(R.id.Oldpassword);
        Newpassword = (EditText) findViewById(R.id.Newpassword);
        change = (Button) findViewById(R.id.Change);
        backtoLogin = (TextView) findViewById(R.id.BackToLogin);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        DB = new DatabaseHelper(this);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Validation

                String Email = email.getText().toString();
                String OldPassword = Oldpassword.getText().toString();
                String NewPassword = Newpassword.getText().toString();
                if (Email.equals("") || OldPassword.equals(""))
                    Toast.makeText(PasswordChanger.this, getString(R.string.user_not_found), Toast.LENGTH_LONG).show();
                else {
                    Boolean checkuserPassword = DB.checkemailPassword(Email, OldPassword);
                    if (checkuserPassword == true) {
                        Boolean passchange = DB.change(Email, NewPassword);
                        if (passchange==false) {
                            Toast.makeText(PasswordChanger.this, "Succesfully", Toast.LENGTH_LONG).show();
                            Intent LoginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(LoginActivityIntent);
                        }

                    } else {
                        Toast.makeText(PasswordChanger.this, getString(R.string.wrong_credentials), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        backtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}