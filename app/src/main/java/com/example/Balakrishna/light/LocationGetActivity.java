package com.example.Balakrishna.light;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class LocationGetActivity extends AppCompatActivity {
    private Spinner Year,Month;
    private EditText Lat,Lon;
    private Button Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getlocation);
        Year = findViewById(R.id.Year_loc);
        Month = findViewById(R.id.Month_loc);
        Lat = findViewById(R.id.Latitude_Get);
        Lon = findViewById(R.id.Longitude_Get);
        Next = findViewById(R.id.Loc_Get_Button);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"2016","2017","2018","2019"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"});
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Month.setAdapter(adapter2);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Lat.getText().toString().isEmpty() && !Lon.getText().toString().isEmpty()){
                    GLOBAL.FLAG=2;
                    GLOBAL.Lat=Double.parseDouble(Lat.getText().toString());
                    GLOBAL.Long=Double.parseDouble(Lon.getText().toString());
                    GLOBAL.YEAR=Year.getSelectedItem().toString();
                    GLOBAL.MONTH=Month.getSelectedItem().toString();
                    startActivity(new Intent(LocationGetActivity.this,CrimeListActivity.class));
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(LocationGetActivity.this,MainActivity.class));
    }
}
