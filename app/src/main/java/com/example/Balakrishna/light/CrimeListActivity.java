package com.example.Balakrishna.light;

//import android.app.Activity;
import android.content.ContentValues;
//import android.content.Intent;
//import android.database.sqlite.SQLiteAbortException;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
//import android.view.View;

import java.util.List;
import java.util.ArrayList;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import javax.microedition.khronos.opengles.GL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrimeListActivity extends AppCompatActivity {
    private SQLiteDatabase fdb;
    public static List<Crime> crimes;
    public static int flag=1;
    private ArrayList<String> mMain = new ArrayList<>();
    private ArrayList<String> mSub = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimelist);
        ForceDBHelper F = new ForceDBHelper(this);
        fdb = F.getWritableDatabase();
        SetList();
    }
    private void SetList(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://data.police.uk/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<List<Crime>> call = api.listCrime(GLOBAL.YEAR +"-"+GLOBAL.MONTH,Double.toString(GLOBAL.Lat),Double.toString(GLOBAL.Long));
        call.enqueue(new Callback<List<Crime>>() {
            @Override
            public void onResponse(Call<List<Crime>> call, Response<List<Crime>> response) {
                flag = 1;
                Log.d("TAG","FLAG");
                Log.d("TAGe",GLOBAL.YEAR+"-"+GLOBAL.MONTH);
                Log.d("TAGd",Double.toString(GLOBAL.Lat));
                Log.d("TAGa",Double.toString(GLOBAL.Long));
                crimes = response.body();
                try{
                if(crimes.size()!=0){
                for(Crime c: crimes){
                    c.setCategory(c.getCategory().replace("-"," ").substring(0, 1).toUpperCase() + c.getCategory().replace("-"," ").substring(1));
                    Log.d("TAGw",c.getCategory());
                    if(c.getOutcomeStatus()==null){
                        OutcomeStatus obj = new OutcomeStatus("Outcome Unavailable",GLOBAL.YEAR+"-"+GLOBAL.MONTH);
                        c.setOutcomeStatus(obj);
                    }
                    switch(c.getOutcomeStatus().getCategory()){
                        case "awaiting-court-result":
                            c.getOutcomeStatus().setCategory("Awaiting court outcome");
                            break;
                        case "court-result-unavailable":
                            c.getOutcomeStatus().setCategory("Court result unavailable");
                            break;
                        case "unable-to-proceed":
                            c.getOutcomeStatus().setCategory("Court case unable to proceed");
                            break;
                        case "local-resolution":
                            c.getOutcomeStatus().setCategory("Local resolution");
                            break;
                        case "no-further-action":
                            c.getOutcomeStatus().setCategory("Investigation complete; no suspect identified");
                            break;
                        case "deprived-of-property":
                            c.getOutcomeStatus().setCategory("Offender deprived of property");
                            break;
                        case "fined":
                            c.getOutcomeStatus().setCategory("Offender fined");
                            break;
                        case "absolute-discharge":
                            c.getOutcomeStatus().setCategory("Offender given absolute discharge");
                            break;
                        case "cautioned":
                            c.getOutcomeStatus().setCategory("Offender given a caution");
                            break;
                        case "drugs-possession-warning":
                            c.getOutcomeStatus().setCategory("Offender given a drugs possession warning");
                            break;
                        case "penalty-notice-issued":
                            c.getOutcomeStatus().setCategory("Offender given a penalty notice");
                            break;
                        case "community-penalty":
                            c.getOutcomeStatus().setCategory("Offender given community sentence");
                            break;
                        case "conditional-discharge":
                            c.getOutcomeStatus().setCategory("Offender given conditional discharge");
                            break;
                        case "suspended-sentence":
                            c.getOutcomeStatus().setCategory("Offender given suspended prison sentence");
                            break;
                        case "imprisoned":
                            c.getOutcomeStatus().setCategory("Offender sent to prison");
                            break;
                        case "other-court-disposal":
                            c.getOutcomeStatus().setCategory("Offender otherwise dealt with");
                            break;
                        case "compensation":
                            c.getOutcomeStatus().setCategory("Offender ordered to pay compensation");
                            break;
                        case "sentenced-in-another-case":
                            c.getOutcomeStatus().setCategory("Suspect charged as part of another case");
                            break;
                        case "charged":
                            c.getOutcomeStatus().setCategory("Suspect charged");
                            break;
                        case "not-guilty":
                            c.getOutcomeStatus().setCategory("Defendant found not guilty");
                            break;
                        case "sent-to-crown-court":
                            c.getOutcomeStatus().setCategory("Defendant sent to Crown Court");
                            break;
                        case "unable-to-prosecute":
                            c.getOutcomeStatus().setCategory("Unable to prosecute suspect");
                            break;
                        case "formal-action-not-in-public-interest":
                            c.getOutcomeStatus().setCategory("Formal action is not in the public interest");
                            break;
                        case "action-taken-by-another-organisation":
                            c.getOutcomeStatus().setCategory("Action to be taken by another organisation");
                            break;
                        case "further-investigation-not-in-public-interest":
                            c.getOutcomeStatus().setCategory("Further investigation is not in the public interest");
                            break;
                        case "further-action-not-in-public-interest":
                            c.getOutcomeStatus().setCategory("Further action is not in the public interest");
                            break;
                        case "under-investigation":
                            c.getOutcomeStatus().setCategory("Under investigation");
                            break;
                        case "status-update-unavailable":
                            c.getOutcomeStatus().setCategory("Status update unavailable");
                            break;
                    }
                        Log.d("TAGq",c.getOutcomeStatus().getCategory());
                        mMain.add(c.getCategory());
                        mSub.add(c.getOutcomeStatus().getCategory());
                }
                if(mMain.size()!=0)
                    initRec();
                else{
                    flag = 0;
                    mMain.add("No Data Available");
                    mSub.add("No Data Available");
                    initRec();
                }
                }
                else{
                    flag = 0;
                    mMain.add("No Data Available");
                    mSub.add("No Data Available");
                    initRec();
                }}
                catch(NullPointerException e){
                    flag = 0;
                    mMain.add("No Data Available");
                    mSub.add("No Data Available");
                    initRec();
                }
            }

            @Override
            public void onFailure(Call<List<Crime>> call, Throwable t) {
                flag = 0;
                mMain.add("No Data Available");
                mSub.add("No Data Available");
                initRec();
            }
        });
    }
    private void initRec(){
        RecyclerView recyclerView = findViewById(R.id.Recyclercrimelist);
        final CrimelistAdapter cla = new CrimelistAdapter(this,mMain,mSub);
        recyclerView.setAdapter(cla);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(flag == 1){
                Crime C = crimes.get(viewHolder.getAdapterPosition());
                    ContentValues cv = new ContentValues();
                    cv.put(ForceTables.ForceDet.COLUMN_CAT,C.getCategory());
                    cv.put(ForceTables.ForceDet.COLUMN_ID,C.getId().toString());
                    cv.put(ForceTables.ForceDet.COLUMN_LATI,C.getLocation().getLatitude());
                    cv.put(ForceTables.ForceDet.COLUMN_LONGI,C.getLocation().getLongitude());
                    cv.put(ForceTables.ForceDet.COLUMN_MONTH,C.getMonth());
                    cv.put(ForceTables.ForceDet.COLUMN_OUTCOME,C.getOutcomeStatus().getCategory());
                    cv.put(ForceTables.ForceDet.COLUMN_STRTNAME,C.getLocation().getStreet().getName());
                    try{
                    fdb.insertOrThrow(ForceTables.ForceDet.TABLE_NAME,null,cv);}
                    catch (SQLiteConstraintException e){
                        //
                    }
                    Toast.makeText(CrimeListActivity.this,"Added to Favorite",Toast.LENGTH_SHORT).show();
                    Log.d("CRIMELIST","Inserted");
                    cla.notifyItemChanged(viewHolder.getAdapterPosition());
            }}};
        ItemTouchHelper i = new ItemTouchHelper(simpleItemTouchCallback);
        i.attachToRecyclerView(recyclerView);
    }
    @Override
    public void onBackPressed(){
        if(GLOBAL.FLAG==1)
        startActivity(new Intent(CrimeListActivity.this,MonthGetActivity.class));
        else
            startActivity(new Intent(CrimeListActivity.this,LocationGetActivity.class));
    }
}
