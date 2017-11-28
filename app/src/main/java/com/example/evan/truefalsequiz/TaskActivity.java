package com.example.evan.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by per6 on 11/7/17.
 */

public class TaskActivity extends AppCompatActivity {

    private TextView taskName, taskDesc, taskDate, avail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskactivity);
        Intent i = getIntent(); //brings over info from main activity
        Tasks thing = i.getParcelableExtra(MainActivity.EXTRA_NAME);
        wireWidgets();
        Tasks clout = getIntent().getParcelableExtra(MainActivity.EXTRA_NAME);
        //displays the text
        taskName.setText(thing.getName());
        taskDesc.setText(thing.getDescription());
        taskDate.setText(thing.getDate());
        avail.setText(thing.getAvailability());
    }

    private void wireWidgets() {
        taskDesc = (TextView) findViewById(R.id.task_desc);
        taskName = (TextView) findViewById(R.id.task_name);
        taskDate = (TextView) findViewById(R.id.task_date);
        avail = (TextView) findViewById(R.id.task_avail);

    }
}
