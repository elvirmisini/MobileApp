package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import com.google.android.material.textfield.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText name,lastname, email, phone, password;
    Button register;
    TextView login;
    boolean isNameValid, isLastNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    TextInputLayout nameError,lastnameError, emailError, phoneError, passError;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        lastnameError = (TextInputLayout) findViewById(R.id.lastnameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        DB =new DatabaseHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
                SQLiteDatabase objDb = new DatabaseHelper(RegisterActivity.this).getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseModelHelper.UsersName, name.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersLastname, lastname.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersEmail, email.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersPhoneNumber, phone.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersPassword, password.getText().toString());


/*
                String Name=name.getText().toString();
                String LastName=lastname.getText().toString();
                String Email=email.getText().toString();
                String Phone=phone.getText().toString();
                String Password=password.getText().toString();
                if(Name.equals("")||LastName.equals("")||Email.equals("")||Phone.equals("")||Password.equals(""))
                    Toast.makeText(RegisterActivity.this, "Enter all fields!", Toast.LENGTH_LONG).show();
                else{
                    Boolean checkuser=DB.checkemailPassword(Email,Password);
                    if (checkuser == false) {

                        Boolean insert=DB.insertData(Name,LastName,Email,Phone,Password);
                        if (insert==true){
                            Toast.makeText(RegisterActivity.this, "Succesfully Registered", Toast.LENGTH_LONG).show();
                            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
//                    mainActivityIntent.putExtra("email", email.getText().toString());
                            startActivity(mainActivityIntent);

                        }else{
                            Toast.makeText(RegisterActivity.this,getString(R.string.wrong_credentials), Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(RegisterActivity.this,"User already exsists!.", Toast.LENGTH_LONG).show();
                    }
                }

 */







                try
                {
                    long id = objDb.insert(DatabaseModelHelper.UsersTable,null,contentValues);
                    if(id>0)
                        Toast.makeText(RegisterActivity.this,getString(R.string.success_message),Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(RegisterActivity.this,ex.getMessage(),Toast.LENGTH_LONG).show();
                }
                finally {
                    objDb.close();
                }


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        //Check for a valid lastname
        if (lastname.getText().toString().isEmpty()) {
            lastnameError.setError(getResources().getString(R.string.lastname_error));
            isLastNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (phone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("name", "");
        String s2 = sh.getString("lastname", "");
        String s3 = sh.getString("email", "");
        int s4 = sh.getInt("phone", 0);

        // Setting the fetched data
        // in the EditTexts
        name.setText(s1);
        lastname.setText(s2);
        email.setText(s3);
        phone.setText(String.valueOf(s4));
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name.getText().toString());
        myEdit.putString("lastname", lastname.getText().toString());
        myEdit.putString("email", email.getText().toString());
        myEdit.putInt("phone", Integer.parseInt(phone.getText().toString()));
        myEdit.apply();
    }

}