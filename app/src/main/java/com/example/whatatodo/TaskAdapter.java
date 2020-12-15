package com.example.whatatodo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private Context context;
    private ArrayList<Tasks> listTasks;
    private ArrayList<Tasks> mArrayList;

    public TaskDatabase myDb;

    public TaskAdapter(Context context, ArrayList<Tasks> listTasks) {
        this.context = context;
        this.listTasks = listTasks;
        this.mArrayList=listTasks;
        myDb = new TaskDatabase(context);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,parent, false);
        return new TaskViewHolder(view);
    }
    private boolean toBoolean(int n){
        return n!=0;
    }
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        final Tasks tasks = listTasks.get(position);

        holder.taskTextView.setText(tasks.getTask());
        holder.dateTextView.setText(tasks.getDate());
        holder.doneCheckBox.setChecked(tasks.getStatus());
        holder.updateTask.setOnClickListener(view -> editTaskDialog(tasks));
        holder.doneCheckBox.setOnCheckedChangeListener((view, checked)-> {
            myDb.updateStatus(listTasks.get(holder.getAbsoluteAdapterPosition()).getId(),
                    checked);});

        //here is the other code i tried
       /*holder.doneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (!b) {
                   myDb.updateStatus(tasks.getId(), false);
               }else{
                   myDb.updateStatus(tasks.getId(),true);
                }
           }
        }
        );*/
        //});


        holder.deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                myDb.deleteTask(tasks.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }


    @Override
    public int getItemCount(){
        return listTasks.size();
    }


    private void editTaskDialog(final Tasks tasks){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_task, null);

        final EditText enter_task = (EditText)subView.findViewById(R.id.newTaskText);
        final EditText enter_date = (EditText)subView.findViewById(R.id.newTaskDate);
        final int itemId  = tasks.getId();
        final boolean done = tasks.getStatus();

        if(tasks!= null){
            enter_task.setText(tasks.getTask());
            enter_date.setText(String.valueOf(tasks.getDate()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit task");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT TASK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String task = enter_task.getText().toString();
                final String date = enter_date.getText().toString();
                final boolean edit_done= done;
                final int id_edit = itemId;


                if(TextUtils.isEmpty(task)){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    myDb.updateTask(new Tasks(id_edit, task, date, edit_done));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
