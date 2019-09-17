package com.alc.uclone.libs.maps;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.ref.WeakReference;

public class MapManager implements OnMapReadyCallback {


    // week reference since Async Task used
    private WeakReference<Activity> mContext;

    private GoogleMap mMap;


    private OnMapReadyCallback mMapReady;

    public MapManager(Activity context, OnMapReadyCallback onMapReady, Fragment fragment) {
        mMapReady = onMapReady;

        mContext = new WeakReference<>(context);

        SupportMapFragment mFragment = (SupportMapFragment) fragment;

        if (mFragment != null)
            mFragment.getMapAsync(this);
    }

    private LatLng setLocation(long lat, long lng) {
        LatLng location = new LatLng(lat, lng);

        setLocation(location);

        updateCameraLocation(location);

        return location;
    }

    public void setLocationAndUpdateCamera(long lat, long lng) {
        LatLng loc = setLocation(lat, lng);

        updateCameraLocation(loc);

    }

    public void setLocation(LatLng loc, @Nullable String title) {
        mMap.addMarker(new MarkerOptions().position(loc).title(title));
    }


    public void setLocation(LatLng loc) {
        mMap.addMarker(new MarkerOptions().position(loc));
    }

    void updateCameraLocation(LatLng loc) {

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,20));
    }

    void setZoom(@NonNull int zoom) {
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().zoom(zoom).build()));
    }

    /**
     * Initialize
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMapReady.onMapReady(googleMap);


    }

    public void setMyLocationEnabled(boolean b) {
        mMap.setMyLocationEnabled(true);
    }
}
