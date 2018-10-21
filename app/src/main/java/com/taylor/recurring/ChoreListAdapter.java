package com.taylor.recurring;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChoreListAdapter extends RecyclerView.Adapter<com.taylor.recurring.ChoreListAdapter.ViewHolder> {
    private ArrayList<String> mNames;
    private ArrayList<String> mDues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView dueIn;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            dueIn = itemView.findViewById(R.id.due_in);
            parentLayout = itemView.findViewById(R.id.chore_list_item);
        }
    }

    public ChoreListAdapter(ArrayList<String> names, ArrayList<String> dues) {
        mNames = names;
        mDues = dues;
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
        holder.name.setText(mNames.get(position));
        holder.dueIn.setText(mDues.get(position));
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }
}
