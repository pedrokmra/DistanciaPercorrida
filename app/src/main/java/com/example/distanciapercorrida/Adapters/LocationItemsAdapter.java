package com.example.distanciapercorrida.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.distanciapercorrida.Classes.LocationItems;
import com.example.distanciapercorrida.R;

import java.util.ArrayList;

public class LocationItemsAdapter extends RecyclerView.Adapter<LocationItemsAdapter.ViewHolder> {
    private ArrayList<LocationItems> locationItemsArrayList;
    private Context context;

    public LocationItemsAdapter(ArrayList<LocationItems> locationItemsArrayList, Context context) {
        this.locationItemsArrayList = locationItemsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationItemsAdapter.ViewHolder holder, int position) {
        LocationItems locationItems = locationItemsArrayList.get(position);
        holder.textViewUserId.setText(locationItems.getUserId());
        holder.textViewLatitude.setText(locationItems.getLatitude());
        holder.textViewLongitude.setText(locationItems.getLongitude());
        holder.textViewDistance.setText(locationItems.getDistance());
        holder.textViewDate.setText(locationItems.getDate());
    }

    @Override
    public int getItemCount() {
        return locationItemsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewUserId, textViewLatitude, textViewLongitude, textViewDistance, textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserId = itemView.findViewById(R.id.textViewUserId);
            textViewLatitude = itemView.findViewById(R.id.textViewLatitude);
            textViewLongitude = itemView.findViewById(R.id.textViewLongitude);
            textViewDistance = itemView.findViewById(R.id.textViewDistance);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
}