package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FirebaseAuthentication extends AppCompatActivity {

    EditText phone, VerificationCode;
    Button submit,GetCode;
    TextView backtoLogin;
    private static final String TAG="FirebaseAuthentication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_authentication);

        phone = (EditText) findViewById(R.id.phone);
        VerificationCode = (EditText) findViewById(R.id.VerificationCode);
        submit = (Button) findViewById(R.id.Submit);
        GetCode = (Button) findViewById(R.id.GetCode);
        backtoLogin = (TextView) findViewById(R.id.BackToLogin);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        backtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        GetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Phone =phone.getText().toString();
                if (Phone.isEmpty()){
                    Toast.makeText(FirebaseAuthentication.this, getString(R.string.phone_error), Toast.LENGTH_LONG).show();
                }
                else {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+383"+Phone, 60, TimeUnit.SECONDS, FirebaseAuthentication.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Log.d(TAG,"onVerificationFailed:"+e.getLocalizedMessage());
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationId, forceResendingToken);

                                    Dialog dialog=new Dialog(FirebaseAuthentication.this);
                                    dialog.setContentView(VerificationCode);

                                    submit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (verificationId.isEmpty())
                                                return;
  //                                          PhoneAuthProvider credential=PhoneAuthProvider.getCredential(verificationId,VerificationCode)
//                                            signInUser(credential);
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);

                                        }
                                    });
                                    dialog.show();
                                }
                            });
                }
            }
        });

    }
    /*
    private void signInUser(PhoneAuthProvider credential){
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent PasswordChangerIntent = new Intent(getApplicationContext(), PasswordChanger.class);
                            startActivity(PasswordChangerIntent);
                            finish();
                        }
                        else {
                            Log.d(TAG,"onComplete"+task.getException().getLocalizedMessage());
                        }
                    }
                })
    }

     */
}