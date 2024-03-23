package com.example.authentication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JournalRVAdapter extends RecyclerView.Adapter<JournalRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<JournalModal> JournalModalArrayList;
    private Context context;

    // constructor
    public JournalRVAdapter(ArrayList<JournalModal> JournalModalArrayList, Context context) {
        this.JournalModalArrayList = JournalModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data 
        // to our views of recycler view item.
        JournalModal modal = JournalModalArrayList.get(position);
        holder.entryTV.setText(modal.getentry());
        holder.entry1TV.setText(modal.getentry1());
        holder.dateTV.setText(modal.getdate());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, delete_entry.class);

                // below we are passing all our values.
                i.putExtra("entry", modal.getentry());
                i.putExtra("entry1", modal.getentry1());
                i.putExtra("date", modal.getdate());

                // starting our activity.
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return JournalModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView entryTV, entry1TV, dateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            entryTV = itemView.findViewById(R.id.idTVEntry);
            entry1TV = itemView.findViewById(R.id.idTVEntry1);
            dateTV = itemView.findViewById(R.id.idTVDate);
        }
    }
}

