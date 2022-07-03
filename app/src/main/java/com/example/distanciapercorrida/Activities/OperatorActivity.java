package com.example.distanciapercorrida.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.distanciapercorrida.Services.FirebaseLoginHelper;
import com.example.distanciapercorrida.Services.LocationService;
import com.example.distanciapercorrida.R;

public class OperatorActivity extends FirebaseLoginHelper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        checkPermissions();

        findViewById(R.id.button_logout).setOnClickListener(v -> {
            logout();
        });
    }

    public void catchLocation(View view) {
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
    }

    public void checkPermissions() {
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                                Log.w("TAG", "checkPermissions: precisa");
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                                Log.w("TAG", "checkPermissions: aproximada");
                            } else {
                                // No location access granted.
                                Log.w("TAG", "checkPermissions: nao tem");
                            }
                        }
                );

        // Verifica se ja tem as permissoes
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
}