package com.example.distanciapercorrida.Activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.distanciapercorrida.Services.FirebaseDatabaseHelper;
import com.example.distanciapercorrida.Services.FirebaseLoginHelper;
import com.example.distanciapercorrida.Classes.LocationItems;
import com.example.distanciapercorrida.Adapters.LocationItemsAdapter;
import com.example.distanciapercorrida.R;

import java.util.ArrayList;

public class AdminActivity extends FirebaseLoginHelper {
    private RecyclerView recyclerView;
    private ArrayList<LocationItems> locationItemsArrayList;
    private LocationItemsAdapter locationItemsAdapter;
    FirebaseDatabaseHelper db = new FirebaseDatabaseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView = findViewById(R.id.recyclerViewLocation);

        locationItemsArrayList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        locationItemsAdapter = new LocationItemsAdapter(locationItemsArrayList, this);

        recyclerView.setAdapter(locationItemsAdapter);

        db.read(locationItemsAdapter, locationItemsArrayList);

        findViewById(R.id.button_logout).setOnClickListener(v -> {
            logout();
        });
    }
}