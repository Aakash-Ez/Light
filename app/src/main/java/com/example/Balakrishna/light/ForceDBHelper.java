package com.example.Balakrishna.light;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.Balakrishna.light.ForceTables.*;
import androidx.annotation.Nullable;

public class ForceDBHelper extends SQLiteOpenHelper {
    public static final String DBname = "CRIMES.db";
    public static final int DBversion = 1;
    public ForceDBHelper(@Nullable Context context) {
        super(context, DBname, null, DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_ForceDet = "CREATE TABLE "+ForceDet.TABLE_NAME+" (" +
                ""+ForceDet.COLUMN_ID+" VARCHAR(50) PRIMARY KEY, "
                +ForceDet.COLUMN_STRTNAME+" VARCHAR(50), "
                +ForceDet.COLUMN_CAT+" VARCHAR(50), "
                +ForceDet.COLUMN_OUTCOME+" VARCHAR(50), "
                +ForceDet.COLUMN_LATI+" VARCHAR(50), "
                +ForceDet.COLUMN_LONGI+" VARCHAR(50), "
                +ForceDet.COLUMN_MONTH+" VARCHAR(50));";
        db.execSQL(CREATE_ForceDet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
