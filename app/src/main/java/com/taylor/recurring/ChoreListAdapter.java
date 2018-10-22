package com.taylor.recurring;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoreListAdapter extends RecyclerView.Adapter<com.taylor.recurring.ChoreListAdapter.ViewHolder> {
    private ArrayList<ChoreItem> mChoreList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView dueIn;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            dueIn = itemView.findViewById(R.id.due_in);
            parentLayout = itemView.findViewById(R.id.chore_list_item);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    CharSequence text = "Hello toast!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });*/
        }
    }

    public ChoreListAdapter(ArrayList<ChoreItem> chores) {
        mChoreList = chores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create a View from our List Item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chore_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Place data into our View
        holder.name.setText(mChoreList.get(position).getName());
        holder.dueIn.setText(mChoreList.get(position).getDueIn());
    }

    @Override
    public int getItemCount() {
        return mChoreList.size();
    }
}
