package com.example.distanciapercorrida.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.distanciapercorrida.Classes.LocationItems;
import com.example.distanciapercorrida.Adapters.LocationItemsAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference locationCollection = db.collection("locationCollection");

    public void save(String latitude, String longitude, String distance) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String date = String.valueOf(Calendar.getInstance().getTime());

        LocationItems locationItems = new LocationItems(userId, latitude, longitude, distance, date);

        locationCollection.add(locationItems).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("onSucces", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("onFailure", "Error adding document", e);
                    }
                });
    }

    public void read(LocationItemsAdapter adapter, ArrayList<LocationItems> locationItemsArrayList) {
        locationCollection.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                LocationItems l = d.toObject(LocationItems.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                locationItemsArrayList.add(l);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            adapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Log.d("onSucces", "No data found");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Log.w("onFailure", "Error reading document", e);
            }
        });
    }
}
