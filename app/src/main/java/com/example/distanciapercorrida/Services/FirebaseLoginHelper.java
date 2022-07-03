package com.example.distanciapercorrida.Services;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLoginHelper extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    boolean response;

    protected boolean login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        response = true;
                    } else {
                        response = false;
                    }
                });
        return response;
    }

    protected void logout() {
        mAuth.signOut();
        finish();
    }
}