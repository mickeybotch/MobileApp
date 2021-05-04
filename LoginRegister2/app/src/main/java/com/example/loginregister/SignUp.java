package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextEmail, textInputEditTextUsername, textInputEditTextPassword;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSignUp.setOnClickListener(v -> {
            String fullname, email, username, password;
            fullname = String.valueOf(textInputEditTextFullname.getText());
            email = String.valueOf(textInputEditTextEmail.getText());
            username = String.valueOf(textInputEditTextUsername.getText());
            password = String.valueOf(textInputEditTextPassword.getText());

            if (!fullname.equals("") && !email.equals("") && !username.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {

                    String[] field = new String[4];
                    field[0] = "fullname";
                    field[1] = "email";
                    field[2] = "username";
                    field[3] = "password";
                    //Creating array for data
                    String[] data = new String[4];
                    data[0] = fullname;
                    data[1] = email;
                    data[2] = username;
                    data[3] = password;
                    PutData putData = new PutData("http://192.168.43.155/LoginRegister/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                           if (result.equals("Sign Up Success")){
                               Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), Login.class);
                               startActivity(intent);
                               finish();

                           }
                           else {
                               Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                           }

                        }
                    }

                });
            }
            else {
                Toast.makeText(getApplicationContext(),"result",Toast.LENGTH_SHORT).show();


            }
        });



    }
}