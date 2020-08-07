package com.example.Balakrishna.light;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
//import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForcesActivity extends AppCompatActivity {
    public static List<Force> force;
    public static int flag=1;
    private EditText Search;
    private ArrayList<String> mMain = new ArrayList<>();
    private ArrayList<String> mSub = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forces);
        Search = findViewById(R.id.Search);
        SetList();
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    private void filter(String s){
        ArrayList<String> filterdMain = new ArrayList<>();
        ArrayList<String> filterdSub = new ArrayList<>();
        for (String x : mMain) {
            if (x.toLowerCase().startsWith(s.toLowerCase())) {
                filterdMain.add(x);
                filterdSub.add(mSub.get(mMain.indexOf(x)));
            }
        }
        RecyclerView recyclerView = findViewById(R.id.Recyclercrimelist);
        CrimelistAdapter cla = new CrimelistAdapter(this,filterdMain,filterdSub);
        recyclerView.setAdapter(cla);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void SetList(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://data.police.uk/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<List<Force>> call = api.listForce();
        call.enqueue(new Callback<List<Force>>() {
            @Override
            public void onResponse(Call<List<Force>> call, Response<List<Force>> response) {
                force= response.body();
                try{
                    if(force.size()!=0)
                        for(Force c: force){
                            mMain.add(c.getName());
                            mSub.add(c.getId());
                        }
                    initRec();}
                catch(NullPointerException e){
                    flag = 0;
                    mMain.add("No Data Available");
                    mSub.add("No Data Available");
                    initRec();
                }
            }

            @Override
            public void onFailure(Call<List<Force>> call, Throwable t) {
                flag = 0;
                mMain.add("No Data Available");
                mSub.add("No Data Available");
                initRec();
            }
        });
    }
    private void initRec(){
        RecyclerView recyclerView = findViewById(R.id.Recyclercrimelist);
        CrimelistAdapter cla = new CrimelistAdapter(this,mMain,mSub);
        recyclerView.setAdapter(cla);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
