package com.example.distanciapercorrida;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseDatabaseHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void save(double latitude, double longitude, double distance) {
        Map<String, Object> locationInsert = new HashMap<>();
        locationInsert.put("userID", FirebaseAuth.getInstance().getCurrentUser().getUid());
        locationInsert.put("latitude", latitude);
        locationInsert.put("longitude", longitude);
        locationInsert.put("distance", distance);
        locationInsert.put("date", Calendar.getInstance().getTime());

        db.collection("locationCollection")
                .add(locationInsert)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("onSucces", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("onFailure", "Error adding document", e);
                    }
                });
    }
}
