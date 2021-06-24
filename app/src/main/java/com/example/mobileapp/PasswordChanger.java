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

import java.util.regex.Pattern;

public class PasswordChanger extends AppCompatActivity {


    EditText email, Oldpassword,Newpassword;
    Button change;
    TextView backtoLogin;
    boolean isPasswordValid;
    TextInputLayout newpassError;
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
        newpassError = (TextInputLayout) findViewById(R.id.newpassError);
        DB = new DatabaseHelper(this);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Validation

                String Email = email.getText().toString();
                String OldPassword = Oldpassword.getText().toString();
                String NewPassword = Newpassword.getText().toString();
                if (Email.equals("") || OldPassword.equals("")){
                    Toast.makeText(PasswordChanger.this, getString(R.string.write_Email_password), Toast.LENGTH_LONG).show();
                }
                else if(NewPassword.equals("")){
                    Toast.makeText(PasswordChanger.this, getString(R.string.write_new_password), Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkuserPassword = DB.checkemailPassword(Email, OldPassword);
                    if (checkuserPassword == true&& PassValidation()==true) {

                        Boolean passchange = DB.change(Email, NewPassword);
                        if (passchange==false) {
                            Toast.makeText(PasswordChanger.this, R.string.success, Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    public static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^"+
                    "(?=.*[0-9])"+"(?=.*[a-z])"+"(?=.*[A-Z])"+"(?=.*[@#$%^&+=.])"+"(?=\\S+$)"+".{6,}"+"$");

    public Boolean PassValidation(){
        if (Newpassword.getText().toString().isEmpty()) {
            newpassError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (!PASSWORD_PATTERN.matcher(Newpassword.getText().toString()).matches()) {
            newpassError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            newpassError.setErrorEnabled(false);
        }
        if (isPasswordValid==false) {
            Toast.makeText(getApplicationContext(), R.string.error_invalid_password, Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}