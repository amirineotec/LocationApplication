package com.example.locationapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    EditText edtLattitude, edtLongitude, edtAdresse, edtLongitudeArr, edtLattitudeArr;

    LocationManager locationManager;

    TextView txtDistance;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        edtLattitude = findViewById(R.id.edtLattitude);
        edtLongitude = findViewById(R.id.edtLongitude);

        edtLongitudeArr = findViewById(R.id.edtLongitudeArr);
        edtLattitudeArr = findViewById(R.id.edtLattitudeArr);

        edtAdresse = findViewById(R.id.edtAdresse);
        txtDistance = findViewById(R.id.txtDistance);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

    }

    public void calculerDistance(View view) {
        float distance = 0;
        txtDistance.setText("");

        try {
            Location locationDepart = new Location("Depart");
            locationDepart.setLongitude(Double.parseDouble(edtLongitude.getText().toString()));
            locationDepart.setLatitude(Double.parseDouble(edtLattitude.getText().toString()));

            Location locationArr = new Location("Arrivee");
            locationArr.setLongitude(Double.parseDouble(edtLongitudeArr.getText().toString()));
            locationArr.setLatitude(Double.parseDouble(edtLattitudeArr.getText().toString()));

            distance = locationDepart.distanceTo(locationArr)/1000 ;

            txtDistance.setText(String.valueOf(distance));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }

    public void getAdresseFromLocation(final Location location, final Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String adresseString = "";

        try {
            List<Address> listAdresse = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (listAdresse != null && listAdresse.size() > 0) {
                Address address = listAdresse.get(0);

                adresseString = address.getAddressLine(0) + " , " + address.getLocality();
                edtAdresse.setText(adresseString);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLocationChanged(Location location) {

        edtLattitude.setText(String.valueOf(location.getLatitude()));
        edtLongitude.setText(String.valueOf(location.getLongitude()));
        getAdresseFromLocation(location, LocationActivity.this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
