package com.taylor.recurring;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ChoreListActivity extends Activity {

    //Testing vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);

        //Initialize a test set of names and due dates
        initTestDataset();

        //Setup Recycler View and Adapter
        RecyclerView choreListRecyclerView = findViewById(R.id.chore_list_recycler_view);
        ChoreListAdapter choreListAdapter = new ChoreListAdapter(mNames, mDues);

        //Plug them together
        choreListRecyclerView.setAdapter(choreListAdapter);

        //Set the layout manager for our recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        choreListRecyclerView.setLayoutManager(layoutManager);
    }


    private void initTestDataset() {
        mNames.add("Clean the toilet");
        mDues.add("Due in 1 day");

        mNames.add("Vacuum the livingroom");
        mDues.add("Due in 3 day");
    }
}
