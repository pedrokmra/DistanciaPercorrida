package com.example.distanciapercorrida.Services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class LocationService extends Service {
    String TAG = "LocationService";
    private Location lastLocation = null;
    FirebaseDatabaseHelper db = new FirebaseDatabaseHelper();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startLocationUpdates();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        LocationRequest locationRequest = createLocationRequest();

        LocationServices
                .getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest
                .setInterval(5000)
                .setFastestInterval(3000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }

    private void calculateDistance(LocationResult locationResult) {
        double latitude = locationResult.getLastLocation().getLatitude();
        double longitude = locationResult.getLastLocation().getLongitude();
        double distance = 0;
        Location loc = locationResult.getLastLocation();

        if (lastLocation != null) {
            distance = loc.distanceTo(lastLocation);
        } else {
            lastLocation = loc;
        }

        Log.i(TAG, "onLocationResult: " + latitude + " - " + longitude + " - " + distance + "M");
        db.save(String.valueOf(latitude), String.valueOf(longitude), String.valueOf(distance));
    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            calculateDistance(locationResult);
        }
    };
}