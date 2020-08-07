package com.example.Balakrishna.light;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Handler;
import android.util.Log;
//import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

public class PositionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static double Lat;
    public static double Lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //float f = 6.0f;
        //mMap.setMinZoomPreference(f);
        LatLng london = new LatLng(GLOBAL.LatDET, GLOBAL.LongDET);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.addMarker(new MarkerOptions().position(london).title("Click on the Map to get Details").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(london,6.0f));
    }
    @Override
    public void onBackPressed(){
        if(GLOBAL.DETKEY==1)
            startActivity(new Intent(PositionActivity.this,CrimeDetFavActivity.class));
        else
            startActivity(new Intent(PositionActivity.this,CrimeDetActivity.class));
    }
}