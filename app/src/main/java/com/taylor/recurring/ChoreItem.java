package com.taylor.recurring;

public class ChoreItem {
    int mID;
    String mName;
    String mDueIn;
    int mInterval;

    public ChoreItem(int id, String name, int interval, String due_in) {
        mID = id;
        mName = name;
        mDueIn = due_in;
        mInterval = interval;
    }

    public int getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public String getDueIn() {
        return mDueIn;
    }

    public int getInterval() { return mInterval; }
}
