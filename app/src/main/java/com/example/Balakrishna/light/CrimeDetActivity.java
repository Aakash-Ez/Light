package com.example.Balakrishna.light;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CrimeDetActivity extends AppCompatActivity {
    Button sm;
    TextView Cat,sn,oc,lati,longi,month,id;
    Integer X;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimedet);
        sm = findViewById(R.id.CDshowmap);
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {GLOBAL.DETKEY=2;
                GLOBAL.LatDET = Double.parseDouble(CrimeListActivity.crimes.get(X).getLocation().getLatitude());
                GLOBAL.LongDET = Double.parseDouble(CrimeListActivity.crimes.get(X).getLocation().getLongitude());
                Log.d(CrimeListActivity.crimes.get(X).getLocation().getLatitude(),CrimeListActivity.crimes.get(X).getLocation().getLongitude());
                startActivity(new Intent(CrimeDetActivity.this,PositionActivity.class));
            }
        });
        X = getIntent().getIntExtra("Position",1);
        Cat = findViewById(R.id.category);
        sn = findViewById(R.id.strtname);
        oc = findViewById(R.id.outcome);
        lati = findViewById(R.id.lati);
        longi = findViewById(R.id.longi);
        month = findViewById(R.id.month);
        id = findViewById(R.id.id);
        Cat.setText(CrimeListActivity.crimes.get(X).getCategory());
        sn.setText(CrimeListActivity.crimes.get(X).getLocation().getStreet().getName());
        oc.setText(CrimeListActivity.crimes.get(X).getOutcomeStatus().getCategory());
        lati.setText("Latitude: "+CrimeListActivity.crimes.get(X).getLocation().getLatitude());
        longi.setText("Longitude: "+CrimeListActivity.crimes.get(X).getLocation().getLongitude());
        month.setText("Month: "+CrimeListActivity.crimes.get(X).getMonth());
        id.setText("ID: "+Integer.toString(CrimeListActivity.crimes.get(X).getId()));
    }
    public void onBackPressed(){
        startActivity(new Intent(CrimeDetActivity.this,CrimeListActivity.class));
    }
}
