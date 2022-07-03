package com.example.distanciapercorrida.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.distanciapercorrida.Services.FirebaseLoginHelper;
import com.example.distanciapercorrida.R;

public class LoginActivity extends FirebaseLoginHelper {
    private EditText email;
    private EditText password;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findComponents();

        buttonLogin.setOnClickListener(v -> {
            boolean response;
            response = login(email.getText().toString(), password.getText().toString());
            startActivities(response);
        });}

    private void startActivities(boolean response) {
        if (response) {
            if (email.getText().toString().equals("pedro@admin.com")) {
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), OperatorActivity.class));
            }
        } else {
            Toast.makeText(this, "Login Inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void findComponents() {
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }
}