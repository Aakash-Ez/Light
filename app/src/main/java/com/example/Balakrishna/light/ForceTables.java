package com.example.Balakrishna.light;

import android.provider.BaseColumns;

public class ForceTables {
    private ForceTables(){}
    public static final class ForceDet implements BaseColumns {
        public static final String TABLE_NAME = "CRIME";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_STRTNAME = "STRTNAME";
        public static final String COLUMN_OUTCOME = "OUTCOME";
        public static final String COLUMN_LATI = "LATI";
        public static final String COLUMN_LONGI = "LONGI";
        public static final String COLUMN_MONTH = "MONTH";
        public static final String COLUMN_CAT = "CATEGORY";

    }
}
