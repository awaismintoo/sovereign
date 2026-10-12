package com.mintoo.sovereign.elite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SovereignDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sovereign_vault.db";
    private static final int DATABASE_VERSION = 1;

    public SovereignDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE trades (id INTEGER PRIMARY KEY AUTOINCREMENT, symbol TEXT, type TEXT, price REAL, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);");
        db.execSQL("CREATE TABLE feed_cache (key TEXT PRIMARY KEY, payload TEXT, updated_at DATETIME DEFAULT CURRENT_TIMESTAMP);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS trades;");
        db.execSQL("DROP TABLE IF EXISTS feed_cache;");
        onCreate(db);
    }

    public boolean logTrade(String symbol, String type, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("symbol", symbol);
        values.put("type", type);
        values.put("price", price);
        long result = db.insert("trades", null, values);
        return result != -1;
    }
}
