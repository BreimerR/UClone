package com.alc.uclone.libs.maps;


import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.ref.WeakReference;

public class MapManager implements OnMapReadyCallback {

    private Long lat;

    private Long lng;

    private GoogleMap mMap;

    public Location location;

    private static String TAG = "MAP_MANAGER";

    // week reference since Async Task used
    private WeakReference<Context> mActivity;


    private OnMapReadyCallback mMapReady;

    private LatLng latLng;

    public MapManager(Context activity, OnMapReadyCallback onMapReady, Fragment fragment) {
        mMapReady = onMapReady;

        mActivity = new WeakReference<>(activity);

        SupportMapFragment mFragment = (SupportMapFragment) fragment;

        if (mFragment != null)
            mFragment.getMapAsync(this);
    }

    private LatLng setLocation(long lat, long lng) {
        latLng = setLatLng(lat, lng);

        setLocation(latLng);

        updateCameraLocation(latLng);

        return latLng;
    }

    private LatLng setLatLng(Long lat, Long lng) {
        this.lat = lat;
        this.lng = lng;
        latLng = new LatLng(lat, lng);

        return latLng;
    }

    public void setLocationAndUpdateCamera(long lat, long lng) {
        updateCameraLocation(setLatLng(lat, lng));
    }

    public void setLocationAndUpdateCameraZoom(long lat, long lng, float zoom) {
        setLocationAndUpdateCamera(lat, lng);
        zoomTo(zoom);
    }

    public void setLocationAndUpdateCameraZoom(LatLng location, float zoom) {
        setLocation(location);
        zoomTo(zoom);
    }

    public void setLocation(LatLng loc, @Nullable String title) {
        mMap.addMarker(new MarkerOptions().position(loc).title(title));

    }


    public void setLocation(LatLng loc) {
        this.latLng = loc;
        mMap.addMarker(new MarkerOptions().position(loc));
    }

    void updateCameraLocation(LatLng loc) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(loc));
    }

    void updateCameraLocationZoom(LatLng loc, int zoom) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom));
    }


    /**
     * TODO
     * implementation not functional
     */
    public void zoomTo(float zoom) {

        if (this.latLng != null) {
            Log.d(TAG, "Zooming the map");
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(this.latLng, zoom));

        }

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

        // enable features
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMapReady.onMapReady(googleMap);

    }

    public void setMyLocationEnabled(boolean b) {
        mMap.setMyLocationEnabled(b);
    }
}
