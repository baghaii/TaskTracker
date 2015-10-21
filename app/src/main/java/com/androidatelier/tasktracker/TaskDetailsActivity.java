package com.androidatelier.tasktracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity {

    String mTaskName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String mTaskName = intent.getStringExtra("itemName");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        TextView task = (TextView) findViewById(R.id.taskName);
        task.setText(mTaskName);
        EditText dueDate = (EditText) findViewById(R.id.editDueDate);
    }
}
