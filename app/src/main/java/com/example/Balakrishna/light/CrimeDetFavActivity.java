package com.example.Balakrishna.light;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import java.util.ArrayList;

public class CrimeDetFavActivity extends AppCompatActivity {
    private TextView Cat,sn,oc,lati,longi,month,id;
    private Button sm;
    private ArrayList<crimefav> crimes = FavoriteActivity.getCrimes();
    private Integer X;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimedet);
        X = getIntent().getIntExtra("Position",1);
        Log.d("TAF", Integer.toString(X));
        sm = findViewById(R.id.CDshowmap);
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.DETKEY=1;
                Log.d("TTT","TAG");
                GLOBAL.LatDET = Double.parseDouble(crimes.get(X).getLati());
                GLOBAL.LongDET = Double.parseDouble(crimes.get(X).getLongi());
                Log.d(crimes.get(X).getLati(),crimes.get(X).getLongi());
                startActivity(new Intent(CrimeDetFavActivity.this,PositionActivity.class));
            }
        });
        Cat = findViewById(R.id.category);
        sn = findViewById(R.id.strtname);
        oc = findViewById(R.id.outcome);
        lati = findViewById(R.id.lati);
        longi = findViewById(R.id.longi);
        month = findViewById(R.id.month);
        id = findViewById(R.id.id);
        Cat.setText(crimes.get(X).getCat());
        sn.setText(crimes.get(X).getStrtName());
        oc.setText(crimes.get(X).getOut());
        lati.setText("Latitude: "+crimes.get(X).getLati());
        longi.setText("Longitude: "+crimes.get(X).getLongi());
        month.setText("Month: "+crimes.get(X).getMonth());
        id.setText("ID: "+crimes.get(X).getID());
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(CrimeDetFavActivity.this,FavoriteActivity.class));
    }
}

