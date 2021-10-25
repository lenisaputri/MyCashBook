package com.example.mycashbook.database;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class OpenHelperDB extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "db_mycashbook.db"; //database name
    public static final int DATABASE_VERSION = 3;

    public OpenHelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
