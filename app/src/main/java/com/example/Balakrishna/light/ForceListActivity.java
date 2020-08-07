package com.example.Balakrishna.light;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForceListActivity extends AppCompatActivity {
    private ArrayList<String> mMain = new ArrayList<>();
    private TextView Name,Id,url,tele;
    private ArrayList<String> mSub = new ArrayList<>();
    private String ID;
    private ArrayList<String> mTitle = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcelist);
        Name = findViewById(R.id.FLNAME);
        Id = findViewById(R.id.FLID);
        url = findViewById(R.id.FLURL);
        tele = findViewById(R.id.FLTELE);
        ID = getIntent().getStringExtra("Position");
        Id.setText("ID: "+ID);
        setList();
    }
    public void setList(){
        Log.d("TAG",ID);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://data.police.uk/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<ForceDetails> call = api.listForces(ID);
    call.enqueue(new Callback<ForceDetails>() {
        @Override
        public void onResponse(Call<ForceDetails> call, Response<ForceDetails> response) {
            ForceDetails fd = response.body();
            Name.setText("Name: "+fd.getName());
            url.setText("URL: "+fd.getUrl());
            tele.setText("Telephone: "+fd.getTelephone());
            for(EngagementMethod c: fd.getEngagementMethods()){
                mMain.add(c.getUrl());
                mSub.add(c.getType());
                mTitle.add(c.getTitle());
            }
            initRec();

        }

        @Override
        public void onFailure(Call<ForceDetails> call, Throwable t) {
            Log.d("error","error");
        }
    });
    }
    private void initRec(){
        RecyclerView recyclerView = findViewById(R.id.RVFL);
        ForcelistAdapter cla = new ForcelistAdapter(this,mMain,mSub,mTitle);
        recyclerView.setAdapter(cla);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
