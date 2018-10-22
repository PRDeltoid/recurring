package com.taylor.recurring;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseHelper {

    public void generate_database_test_data(Context context) {
        SQLiteDatabase db = Database.get_writable_db(context);

        ContentValues values = new ContentValues();
        values.put(DatabaseContracts.Chore.COLUMN_NAME_NAME, "Wash the sink");
        values.put(DatabaseContracts.Chore.COLUMN_NAME_INTERVAL, 5);
        values.put(DatabaseContracts.Chore.COLUMN_NAME_USERID, 0);
        long newRowID = db.insert(DatabaseContracts.Chore.TABLE_NAME, null, values);
    }

    public ArrayList<ChoreItem> get_chore_list(Context context) {
        String[] ENTRY_PROJECTION = {
                DatabaseContracts.Chore._ID,
                DatabaseContracts.Chore.COLUMN_NAME_NAME,
                DatabaseContracts.Chore.COLUMN_NAME_INTERVAL
        };

        SQLiteDatabase db = Database.get_readable_db(context);

        Cursor query = db.query(DatabaseContracts.Chore.TABLE_NAME,
                ENTRY_PROJECTION,
                null,
                null,
                null,
                null,
                DatabaseContracts.Chore.COLUMN_NAME_INTERVAL + " desc"
        );

        ArrayList<ChoreItem> chores = new ArrayList<>();

        while(query.moveToNext()) {
            chores.add(new ChoreItem(query.getInt(query.getColumnIndex(DatabaseContracts.Chore._ID)), query.getString(query.getColumnIndex(DatabaseContracts.Chore.COLUMN_NAME_NAME)), query.getInt(query.getColumnIndex((DatabaseContracts.Chore.COLUMN_NAME_INTERVAL))), "Due in " + query.getString(query.getColumnIndex(DatabaseContracts.Chore.COLUMN_NAME_INTERVAL)) + " Days"));
        }

        return chores;
    }

    public boolean insert_new_chore(Context context, ChoreItem choreItem) {
        SQLiteDatabase db = Database.get_writable_db(context);

        ContentValues values = new ContentValues();
        values.put(DatabaseContracts.Chore.COLUMN_NAME_NAME, choreItem.getName());
        values.put(DatabaseContracts.Chore.COLUMN_NAME_INTERVAL, choreItem.getInterval());
        values.put(DatabaseContracts.Chore.COLUMN_NAME_USERID, 0);
        long newRowID = db.insert(DatabaseContracts.Chore.TABLE_NAME, null, values);

        if(newRowID == -1) {
            return false;
        }
        return true;
    }
}
