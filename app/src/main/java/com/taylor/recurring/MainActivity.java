package com.taylor.recurring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ChoreItem> mChoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get list of Chores from local DB
        updateChoreList();

        setRecyclerLayoutManger();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 5) {
            updateChoreList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
         if (id == R.id.action_new_chore) {
            Intent intent = new Intent(MainActivity.this, NewChoreActivity.class);
            intent.putExtra("key", -1); //id of a "new" chore is -1
            MainActivity.this.startActivityForResult(intent, 5);

            updateChoreList();
         }

        return super.onOptionsItemSelected(item);
    }

    private void setRecyclerLayoutManger() {
        RecyclerView choreListRecyclerView = findViewById(R.id.chore_list_recycler_view);
        //Set the layout manager for our recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        choreListRecyclerView.setLayoutManager(layoutManager);
    }

    private void updateChoreList() {
        //Find our recycler view in the activity
        RecyclerView choreListRecyclerView = findViewById(R.id.chore_list_recycler_view);
        //Get list of Chores from local DB
        DatabaseHelper db_helper = new DatabaseHelper();
        mChoreList = db_helper.get_chore_list(getApplicationContext());
        //Construct our adapter
        ChoreListAdapter choreListAdapter = new ChoreListAdapter(mChoreList);

        //Plug them together
        choreListRecyclerView.setAdapter(choreListAdapter);
    }

}
