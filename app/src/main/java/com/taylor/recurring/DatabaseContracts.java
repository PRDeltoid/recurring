package com.taylor.recurring;

import android.provider.BaseColumns;

final class DatabaseContracts {
    private DatabaseContracts () {}

    public static class ChoreEntry implements BaseColumns {
        public static final String TABLE_NAME="choreentries";
        public static final String COLUMN_NAME_DATETIME="date";
        public static final String COLUMN_NAME_USERID="userid";
        public static final String COLUMN_NAME_CHOREID="choreid";
    }

    public static class Chore implements BaseColumns {
        public static final String TABLE_NAME="chores";
        public static final String COLUMN_NAME_INTERVAL="interval";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_USERID="userid";
    }

    public static class User implements BaseColumns {
        public static final String TABLE_NAME="users";
        public static final String COLUMN_NAME_USERNAME="username";
    }
}
