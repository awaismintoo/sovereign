package com.mintoo.sovereign.elite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SovereignDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sovereign_elite_vault.db";
    private static final int DATABASE_VERSION = 1;
    public SovereignDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS market_cache (id INTEGER PRIMARY KEY AUTOINCREMENT, symbol TEXT, price TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);");
        db.execSQL("CREATE TABLE IF NOT EXISTS app_metadata (key TEXT PRIMARY KEY, value TEXT);");
        db.execSQL("INSERT OR REPLACE INTO app_metadata (key, value) VALUES ('patent_holder', 'Awais Rajpoot (awaisrajpoot2@yahoo.com)');");
        db.execSQL("INSERT OR REPLACE INTO app_metadata (key, value) VALUES ('address', 'Broadway Street, Greens Avenue, Park Road, Islamabad, Pakistan');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS market_cache;");
        db.execSQL("DROP TABLE IF EXISTS app_metadata;");
        onCreate(db);
    }
}
