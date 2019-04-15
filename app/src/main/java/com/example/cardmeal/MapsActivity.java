package com.example.cardmeal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.content.res.ResourcesCompat;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

public class MapsActivity extends MainActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private String name;
    private ArrayList<Integer> mapIcons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: load banner/card if restaurant selected
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_maps, null), params);

        mapIcons = new ArrayList<Integer>() {{
            add(R.mipmap.ville_grill_marker);
            add(R.mipmap.chick_fil_a_marker);
            add(R.mipmap.einstein_bros_marker);
            add(R.mipmap.mcalisters_marker);
            add(R.mipmap.panda_express_logo);
            add(R.mipmap.sandwich_shack_marker);
            add(R.mipmap.papa_johns_marker);
            add(R.mipmap.src_cafe_marker);
            add(R.mipmap.subway_marker);
            add(R.mipmap.twisted_taco_marker);
            add(R.mipmap.wendys_marker);
            add(R.mipmap.starbucks_marker);
            add(R.mipmap.starbucks_marker);
            add(R.mipmap.starbucks_marker);
            add(R.mipmap.aqua_sushi_marker);
            add(R.mipmap.greens_to_go_marker);
            add(R.mipmap.olilo_marker);
        }};

        // Default location: University of Louisville
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("lat", 38.2130);
        longitude = intent.getDoubleExtra("long", -85.7586);
        name = intent.getStringExtra("name");

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
        LatLng location = new LatLng(latitude, longitude);

        int count = 0;
        for (RestaurantCardData rd : RestaurantData.getInstance().restaurantCards) {
            double lo = longitude;
            double la = latitude;
            try {
                la = Double.parseDouble(rd.latitude);
                lo = Double.parseDouble(rd.longitude);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(la, lo))
                .title(rd.name);
            mMap.addMarker(marker).setIcon(BitmapDescriptorFactory.fromResource(mapIcons.get(count++)));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.5f));
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

