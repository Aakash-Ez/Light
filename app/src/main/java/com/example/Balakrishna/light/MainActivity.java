package com.example.Balakrishna.light;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button maps,loc,force,fav;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maps = findViewById(R.id.Maps);
        loc = findViewById(R.id.Location_Search);
        force = findViewById(R.id.Forces);
        fav = findViewById(R.id.Favorites);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.KEY=0;
                startActivity(new Intent(MainActivity.this,FavoriteActivity.class));
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.KEY=1;
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.KEY=1;
                startActivity(new Intent(MainActivity.this,LocationGetActivity.class));
            }
        });
        force.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.KEY=2;
                startActivity(new Intent(MainActivity.this,ForcesActivity.class));
            }
        });
    }
}
