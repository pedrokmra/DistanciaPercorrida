package com.example.distanciapercorrida.Activities;

import android.os.Bundle;

import com.example.distanciapercorrida.FirebaseLoginHelper;
import com.example.distanciapercorrida.R;

public class AdminActivity extends FirebaseLoginHelper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findViewById(R.id.button_logout).setOnClickListener(v -> {
            logout();
        });
    }
}