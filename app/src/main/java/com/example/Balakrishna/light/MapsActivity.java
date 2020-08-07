package com.example.Balakrishna.light;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.Window;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Handler;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import com.google.android.gms.maps.model.MarkerOptions;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Button c;
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
        float f = 6.0f;
        mMap.setMinZoomPreference(f);
        LatLng london = new LatLng(54.00366, -2.54785);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.addMarker(new MarkerOptions().position(london).title("Click on the Map to get Details").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(london,6.0f));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Location").draggable(false));
                Lat = latLng.latitude;
                GLOBAL.Lat=Lat;
                Lon = latLng.longitude;
                GLOBAL.Long=Lon;
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        GLOBAL.FLAG=1;
                        final Dialog fbDialogue = new Dialog(MapsActivity.this, android.R.style.Theme_Black_NoTitleBar);
                        fbDialogue.setContentView(R.layout.layout_confirmation);
                        //fbDialogue.getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(200, 0, 0, 0)));
                        //fbDialogue.getWindow().setGravity(Gravity.CENTER);
                        fbDialogue.setCancelable(true);
                        //fbDialogue.getWindow().setGravity(Gravity.BOTTOM);
                        fbDialogue.show();
                        c = fbDialogue.findViewById(R.id.Confirm);
                        c.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                fbDialogue.dismiss();
                                startActivity(new Intent(MapsActivity.this,MonthGetActivity.class));
                                MapsActivity.this.finish();
                            }
                        });
                    }
                }, 100);
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(MapsActivity.this,MainActivity.class));
    }
}