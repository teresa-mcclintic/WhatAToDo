package com.example.whatatodo;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


@SuppressWarnings("ALL")
public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView taskTextView;
    public CheckBox doneCheckBox;
    public TextView dateTextView;
    public ImageButton deleteBtn;
    public ImageView deleteTask;
    public ImageView updateTask;

    public TaskViewHolder(@NonNull View itemView) {

        super(itemView);
        taskTextView = itemView.findViewById(R.id.task_name);
        dateTextView = itemView.findViewById(R.id.date);
        doneCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        deleteTask = (ImageView)itemView.findViewById(R.id.delete_task);
        updateTask = (ImageView)itemView.findViewById(R.id.edit_task);
    }
    public TaskViewHolder(@NonNull View itemView, boolean b) {

        super(itemView);
        taskTextView = itemView.findViewById(R.id.task_name);
        dateTextView = itemView.findViewById(R.id.date);
        doneCheckBox = itemView.findViewById(R.id.checkBox);
        deleteTask = (ImageView)itemView.findViewById(R.id.delete_task);
        updateTask = (ImageView)itemView.findViewById(R.id.edit_task);

        doneCheckBox.setChecked(b);
    }
}