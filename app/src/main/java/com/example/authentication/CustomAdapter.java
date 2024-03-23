package com.example.authentication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    private Activity activity;
    private ArrayList list_id, list_todo, list_date;


    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList task, ArrayList duedate){
        this.activity = activity;
        this.context = context;
        this.list_id = id;
        this.list_todo = task;
        this.list_date = duedate;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.list_task.setText(String.valueOf(list_todo.get(position)));
        holder.list_date.setText(String.valueOf(list_date.get(position)));
        // Check if the task is overdue based on the due date
        boolean isOverdue = checkIfTaskIsOverdue(String.valueOf(list_date.get(position)));


        if (isOverdue) {
            holder.status.setText("Overdue!!");
            holder.status.setTextColor(Color.RED);
            holder.list_task.setTextColor(Color.RED); // Change text color to red
        } else {
            holder.list_task.setTextColor(Color.BLACK); // Set text color to default
        }


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(list_id.get(position)));
                intent.putExtra("title", String.valueOf(list_todo.get(position)));
                intent.putExtra("duedate", String.valueOf(list_date.get(position)));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {


        return list_id.size();
    }


    private boolean checkIfTaskIsOverdue(String dueDate) {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Parse the due date into a Calendar object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar dueDateCalendar = Calendar.getInstance();
        try {
            dueDateCalendar.setTime(Objects.requireNonNull(sdf.parse(dueDate)));
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Handle parsing error gracefully
        }
        if(currentDate.before(dueDateCalendar)){
            return false;
        }
        else if((currentDate.get(Calendar.YEAR) == dueDateCalendar.get(Calendar.YEAR) &&
                currentDate.get(Calendar.MONTH) == dueDateCalendar.get(Calendar.MONTH) &&
                currentDate.get(Calendar.DAY_OF_MONTH) == dueDateCalendar.get(Calendar.DAY_OF_MONTH))) {
            return false;
        }
        else {
            return true;
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView list_task, list_date, status;
        LinearLayout mainLayout;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_task = itemView.findViewById(R.id.task);
            list_date = itemView.findViewById(R.id.duedate);
            status = itemView.findViewById(R.id.overdue);
            mainLayout = itemView.findViewById(R.id.mainLayout);


            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}


