package com.example.whatatodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private TaskDatabase mDatabase;
    private ArrayList<Tasks> allTasks=new ArrayList<>();
    private TaskAdapter mAdapter;
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.task_list);

        RecyclerView taskView = (RecyclerView)findViewById(R.id.tasksRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        taskView.setLayoutManager(linearLayoutManager);
        taskView.setHasFixedSize(true);
        mDatabase = new TaskDatabase(this);
        allTasks = mDatabase.listTasks();

        if(allTasks.size() >0) {
            taskView.setVisibility(View.VISIBLE);
            mAdapter = new TaskAdapter(this, allTasks);
            taskView.setAdapter(mAdapter);

        }else {
            taskView.setVisibility(View.GONE);
            Toast.makeText(this, "All current tasks completed!", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

            addTaskDialog();
            }
        });
    }

    private void addTaskDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_task, null);

        final EditText taskField = (EditText)subView.findViewById(R.id.newTaskText);
        final EditText dateField = (EditText)subView.findViewById(R.id.newTaskDate);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new Task");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD TASK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String task = taskField.getText().toString();
                final String date = dateField.getText().toString();
                final boolean status = false;


                if (TextUtils.isEmpty(task)) {
                    Toast.makeText(MainActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                } else {
                    Tasks newTask = new Tasks(task, date, status);
                    mDatabase.addTasks(newTask);

                    finish();
                    startActivity(getIntent());

                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }

        });
        builder.show();

    }

    //@Override
    protected void onResume(Bundle savedInstanceState){
        super.onResume();
       // Button button = findViewById(R.id.checkBox);
        //button.setOnClickListener((view -> startActivity(getIntent())));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}