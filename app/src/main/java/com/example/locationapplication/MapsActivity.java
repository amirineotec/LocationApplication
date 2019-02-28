package com.example.locationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng abidjan=new LatLng(5.307346, -3.9934815);
        map.addMarker(new MarkerOptions().position(abidjan).title("Abidjan"));


        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                .include(abidjan)
                .build();
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 100));


    }
}
