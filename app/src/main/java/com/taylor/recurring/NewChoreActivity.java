package com.taylor.recurring;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewChoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);

        Intent intent = getIntent();
        int value = intent.getIntExtra("key", 0);

        Button button = (Button) findViewById(R.id.save_chore);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Context context = getApplicationContext();

                EditText chore_name = (EditText) findViewById(R.id.chore_name);
                EditText interval = (EditText) findViewById(R.id.interval);
                String chore_name_string = chore_name.getText().toString();
                int chore_interval_int = Integer.parseInt(interval.getText().toString());

                //Add the chore to the database
                DatabaseHelper db_helper = new DatabaseHelper();
                boolean success = db_helper.insert_new_chore(context, new ChoreItem(-1, chore_name_string, chore_interval_int, ""));

                //Display a status toast
                CharSequence toastText;

                if(success) {
                    toastText = "New Chore Added";
                } else {
                    toastText = "Failed to Add Chore";
                }

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toastText, duration);
                toast.show();

                //Exit the activity after saving the chore (may remove this later?)
                setResult(5, new Intent());
                finish();
            }
        });
    }
}
