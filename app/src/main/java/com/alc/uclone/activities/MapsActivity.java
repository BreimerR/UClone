package com.alc.uclone.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.alc.uclone.libs.maps.MapManager;
import com.alc.uclone.models.Car;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener, OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    public static float DEFAULT_ZOOM = 15.0f;

    private final int LOCATION_REQUEST_CURRENT = 0;

    private MapManager mMapMapManager;

    private String TAG = "MAPS_ACTIVITY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initAppComponents(savedInstanceState);
    }

    private void initAppComponents(@Nullable Bundle savedInstanceState) {
        mMapMapManager = new MapManager(this, this, getSupportFragmentManager().findFragmentById(R.id.map));

        initializeTextView();
    }


    private void initializeTextView() {
        TextView whereTo = findViewById(R.id.whereTo);
        TextView selectLoc = findViewById(R.id.selectLoc);


        whereTo.setOnClickListener(view -> {
            // TODO(open set location activity)
        });

        selectLoc.setOnClickListener(view -> {
            // TODO(open previous visited places activity )
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng loc = currentLocation();

        String lat = Double.toString(loc.latitude);
        String lng = Double.toString(loc.latitude);

        mMapMapManager.setLocationAndUpdateCameraZoom(currentLocation(), DEFAULT_ZOOM);

        Log.d(TAG, "adding tag to view");

        // mMapMapManager.addCar(new Car("cad", "true", "0.2", lat,lng));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if
        (requestCode == LOCATION_REQUEST_CURRENT) {// TODO issue iterative
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMapMapManager.setLocation(currentLocation());

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


    }

    private LatLng currentLocation() {

        LocationManager lManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!checkLocationPermission()) {

            // TODO preference of showing location permission request

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};

            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_REQUEST_CURRENT);
        }


        if (lManager != null) {

            Location myLocation = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (myLocation != null) {

                return new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            } else {
                lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }

        mMapMapManager.setMyLocationEnabled(true);

        return new LatLng(0, 0);
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {

        }
    }

    @Override
    public void onLocationChanged(Location location) {

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
