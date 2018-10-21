package com.taylor.recurring;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {

    public Cursor get_chore_list_cursor(Context context) {
        String[] ENTRY_PROJECTION = {
                DatabaseContracts.Chore._ID,
                DatabaseContracts.Chore.COLUMN_NAME_NAME,
        };

        SQLiteDatabase db = Database.get_readable_db(context);

        return db.query(DatabaseContracts.Chore.TABLE_NAME,
                ENTRY_PROJECTION,
                null,
                null,
                null,
                null,
                DatabaseContracts.Chore._ID + " desc"
        );
    }
}
