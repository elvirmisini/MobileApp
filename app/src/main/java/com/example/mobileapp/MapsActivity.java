package com.example.mobileapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Prishtine = new LatLng(42.649077, 21.165609);
        mMap.addMarker(new MarkerOptions().position(Prishtine).title("Marker in University of Prishtina (FIEK) in Kosovo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Prishtine));
        setUpClusterer();
    }

    // Declare a variable for the cluster manager.
    private ClusterManager<MyItem> clusterManager;

    private void setUpClusterer() {
        // Position the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.647092, 21.128408), 8));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = new ClusterManager<MyItem>(this, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 42.647092;
        double lng = 21.138408;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / -60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng, "Office " + i, "Kosovo " + i);
            clusterManager.addItem(offsetItem);

            // Set the lat/long coordinates for the marker.
               lat = 42.647;
               lng = 21.138;

            // Set the title and snippet strings.
            String title = "This is the title";
            String snippet = "and this is the snippet.";

            // Create a cluster item for the marker and set the title and snippet using the constructor.
            MyItem infoWindowItem = new MyItem(lat, lng, title, snippet);

            // Add the cluster item (marker) to the cluster manager.
            clusterManager.addItem(infoWindowItem);
        }
    }

}