package com.myapp.mytodos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;


public class TasksActivity extends AppCompatActivity {

    ListView taskListView;
    List<String> tasks;
    List<String> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        tasks = FileHelper.readData(this);
        dates = FileHelperDates.readData(this);
        TaskAdapter taskAdapter = new TaskAdapter(this, tasks, dates);
        taskListView = findViewById(R.id.taskListView);
        //using the custom adapter with list view
        taskListView.setAdapter(taskAdapter);

        //adding onClickListener to add new task button that launches add task activity
        Button addNewTaskBtn = findViewById(R.id.addNewTaskBtn);

        addNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), AddTaskActivity.class);
                startActivity(startIntent);
            }
        });
    }
}






