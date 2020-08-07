package com.example.Balakrishna.light;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Balakrishna.light.ForceTables.ForceDet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class FavoriteActivity extends AppCompatActivity {
    private EditText Search;
    private static ArrayList<crimefav> crimes = new ArrayList<crimefav>();

    public static ArrayList<crimefav> getCrimes() {
        return crimes;
    }

    private SQLiteDatabase fdb;
    private ArrayList<String> mMain = new ArrayList<>();
    private ArrayList<String> mSub = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forces);
        Search = findViewById(R.id.Search);
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
        ForceDBHelper F = new ForceDBHelper(this);
        fdb = F.getReadableDatabase();
        setList();
    }
    private void setR(){
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
                //Log.d(crimes.get(viewHolder.getAdapterPosition()).getCat(),Integer.toString(viewHolder.getAdapterPosition()));
                //Log.d(crimes.get(viewHolder.getAdapterPosition()+1).getCat(),Integer.toString(viewHolder.getAdapterPosition()+1));
                fdb.delete(ForceDet.TABLE_NAME,ForceDet.COLUMN_ID+"="+crimes.get(viewHolder.getAdapterPosition()).getID(),null);
                crimes.remove(viewHolder.getAdapterPosition());
                Toast.makeText(FavoriteActivity.this,"Removed from Favorite",Toast.LENGTH_SHORT).show();
                cla.removeItem(viewHolder.getAdapterPosition());
                setList();

            }};
        ItemTouchHelper i = new ItemTouchHelper(simpleItemTouchCallback);
        i.attachToRecyclerView(recyclerView);
    }
    private void setList(){
        crimes.clear();
        mMain.clear();
        mSub.clear();
        String[] Project = {ForceDet.COLUMN_ID,ForceDet.COLUMN_STRTNAME,ForceDet.COLUMN_CAT,ForceDet.COLUMN_OUTCOME,ForceDet.COLUMN_LATI,ForceDet.COLUMN_LONGI,ForceDet.COLUMN_MONTH};
        Cursor cursor = fdb.query(ForceTables.ForceDet.TABLE_NAME,Project,null,null,null,null,ForceDet.COLUMN_CAT);
        int i=0;
        while(i<cursor.getCount()){
            cursor.moveToNext();
            mMain.add(cursor.getString(2));
            mSub.add(cursor.getString(3));
            crimefav c = new crimefav();
            //Log.d("ID",cursor.getString(0));
            Log.d("Cat",cursor.getString(2));
            c.setID(cursor.getString(0));
            c.setStrtName(cursor.getString(1));
            c.setCat(cursor.getString(2));
            c.setOut(cursor.getString(3));
            c.setLati(cursor.getString(4));
            c.setLongi(cursor.getString(5));
            c.setMonth(cursor.getString(6));
            crimes.add(c);
            i++;
        }
        Log.d("TAG",Integer.toString(cursor.getCount()));
        for(int j=0;j<i;j++){
            Log.d("ID"+Integer.toString(j),crimes.get(j).getCat());
        }
        setR();
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
    @Override
    public void onBackPressed(){
        startActivity(new Intent(FavoriteActivity.this,MainActivity.class));
    }
}
