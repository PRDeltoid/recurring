package com.taylor.recurring;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recurring_app.db";

    private static final String SQL_CREATE_ENTRIES_TABLE =
        "CREATE TABLE " + DatabaseContracts.ChoreEntry.TABLE_NAME + " (" +
            DatabaseContracts.ChoreEntry._ID +                  " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DatabaseContracts.ChoreEntry.COLUMN_NAME_DATETIME + " DATE NOT NULL, " +
            DatabaseContracts.ChoreEntry.COLUMN_NAME_CHOREID +  " INTEGER REFERENCES " + DatabaseContracts.Chore.TABLE_NAME + ", " +
            DatabaseContracts.ChoreEntry.COLUMN_NAME_USERID +   " INTEGER REFERENCES " + DatabaseContracts.User.TABLE_NAME +
        ")";

    private static final String SQL_CREATE_CHORE_TABLE =
        "CREATE TABLE " + DatabaseContracts.Chore.TABLE_NAME + " (" +
            DatabaseContracts.Chore._ID +                  " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DatabaseContracts.Chore.COLUMN_NAME_INTERVAL + " INTEGER NOT NULL," +
            DatabaseContracts.Chore.COLUMN_NAME_NAME +     " TEXT NOT NULL, " +
            DatabaseContracts.Chore.COLUMN_NAME_USERID +   " INTEGER REFERENCES " + DatabaseContracts.User.TABLE_NAME +
        ")";

    private static final String SQL_CREATE_USER_TABLE =
        "CREATE TABLE " + DatabaseContracts.User.TABLE_NAME + " (" +
                DatabaseContracts.User._ID +                  " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContracts.User.COLUMN_NAME_USERNAME + " TEXT NOT NULL" +
        ")";

    private static final String SQL_DELETE_ENTRIES_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseContracts.ChoreEntry.TABLE_NAME;
    private static final String SQL_DELETE_CHORE_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseContracts.Chore.TABLE_NAME;
    private static final String SQL_DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseContracts.User.TABLE_NAME;

    private static Database mDbHelper = null;

    private Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHORE_TABLE);
        db.execSQL(SQL_CREATE_ENTRIES_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_ver, int new_ver) {
        db.execSQL(SQL_DELETE_CHORE_TABLE);
        db.execSQL(SQL_DELETE_ENTRIES_TABLE);
        db.execSQL(SQL_DELETE_USER_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int old_version, int new_version) {
        onUpgrade(db, old_version, new_version);
    }

    static public SQLiteDatabase get_readable_db(Context context) {
        acquire_helper_instance(context);
        return mDbHelper.getReadableDatabase();
    }


    static public SQLiteDatabase get_writable_db(Context context) {
        acquire_helper_instance(context);
        return mDbHelper.getWritableDatabase();
    }

    static private void acquire_helper_instance(Context context) {
        if(mDbHelper == null) {
            mDbHelper = new Database(context);
        }
    }


}
