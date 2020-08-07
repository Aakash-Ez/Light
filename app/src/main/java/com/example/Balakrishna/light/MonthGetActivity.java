package com.example.Balakrishna.light;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import javax.microedition.khronos.opengles.GL;

public class MonthGetActivity extends AppCompatActivity {
    private Spinner Year,Month;
    public static String Y,M;
    private Button Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthget);
        Year = findViewById(R.id.Year);
        Month = findViewById(R.id.Month);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"2016","2017","2018","2019"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"});
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Month.setAdapter(adapter2);
        Next = findViewById(R.id.getd);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Y = Year.getSelectedItem().toString();
                GLOBAL.YEAR=Y;
                M = Month.getSelectedItem().toString();
                GLOBAL.MONTH = M;
                startActivity(new Intent(MonthGetActivity.this,CrimeListActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(MonthGetActivity.this,MapsActivity.class));
    }
}
