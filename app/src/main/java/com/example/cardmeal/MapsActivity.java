package com.example.cardmeal;

import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.widget.LinearLayout.LayoutParams;

public class MapsActivity extends MainActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);


        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_maps, null), params);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng uofl = new LatLng(38.215, -85.758);
        mMap.addMarker(new MarkerOptions().position(uofl).title("Marker in Louisville"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uofl, 13.8f));
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_maps;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_map;
    }
}

