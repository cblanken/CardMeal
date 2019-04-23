package com.example.cardmeal;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends MainActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private String name;
    private int index;
    private HashMap<String, Integer> mapIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_maps, null), params);
        mapIcons = new HashMap<String, Integer>();
        mapIcons = new HashMap<String, Integer>() {{
            put("The Ville Grill", R.mipmap.ville_grill_marker);
            put("Chick-fil-A", R.mipmap.chick_fil_a_marker);
            put("Einstein Bros. Bagels", R.mipmap.einstein_bros_marker);
            put("McAlisters Deli", R.mipmap.mcalisters_marker);
            put("Panda Express", R.mipmap.panda_express_logo);
            put("Sandwich Shack", R.mipmap.sandwich_shack_marker);
            put("Papa Johns", R.mipmap.papa_johns_marker);
            put("SRC Cafe", R.mipmap.src_cafe_marker);
            put("Subway at Davidson Hall", R.mipmap.subway_marker);
            put("Twisted Taco", R.mipmap.twisted_taco_marker);
            put("Wendys", R.mipmap.wendys_marker);
            put("Starbucks at Ekstrom Library", R.mipmap.starbucks_marker);
            put("Starbucks at Health Sciences Center", R.mipmap.starbucks_marker);
            put("Starbucks at SAC East", R.mipmap.starbucks_marker);
            put("Aqua Sushi by Drakes", R.mipmap.aqua_sushi_marker);
            put("Greens to Go", R.mipmap.greens_to_go_marker);
            put("Olilo", R.mipmap.olilo_marker);
        }};

        // Default location: University of Louisville
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("lat", 38.2170);
        longitude = intent.getDoubleExtra("long", -85.7586);
        name = intent.getStringExtra("name");
        index = intent.getIntExtra("index", -1);
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
            Log.d("NAMEASDFJKL;", rd.name.replace("’", ""));
            mMap.addMarker(marker).setIcon(BitmapDescriptorFactory.fromResource(mapIcons.get(rd.name.replaceAll("’", "").replaceAll("'", ""))));


        }

        if (getIntent().getStringExtra("name") != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.5f));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15.5f));
        }
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

