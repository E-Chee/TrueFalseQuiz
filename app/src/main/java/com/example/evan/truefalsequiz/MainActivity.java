package com.example.evan.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton, falseButton, nextButton, finishButton, displayTaskInfo;
    private TextView questionText, newTask;
    private ImageView upArrow;
    private int streak;
    private List<Question> questionBank;
    private List<Tasks> tasks, history;
    private List<CheckBox> checkBoxes;
    private ListView taskList;
    private int questionNumber, score;
    private float x1, x2, y1, y2;
    private ArrayAdapter<Tasks> adapter;
    private CheckBox check1, check2;
    public static final String EXTRA_NAME = "CLOUT";
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for no title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        wireWidget();
        initQuestionBank();
        setListeners();
        new Date().g
        streak = 0;
        //load the 1st question
        //check if we're resuming from a previous state
        if (savedInstanceState != null) {
            questionNumber = savedInstanceState.getInt("question we're on", 0);
        }
        questionText.setText(questionBank.get(questionNumber).getQuestionText());
        score = 0;
        //set textviews
        initTaskList();
        initCheckBoxList();
        initHistoryList();
        //create the adapter that will be the go between from the list to the listview
        adapter = new ArrayAdapter<Tasks>(this, R.layout.item, tasks){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
        };
        //set the adapter to the listview
        taskList.setAdapter(adapter);
        taskList.setOnItemClickListener(new ListView.OnItemClickListener() {
            //when clicked, each item will open main activity and show name, description, and pic
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent i = new Intent(MainActivity.this, TaskActivity.class); //send info to new activity
                //get the object from the arraylist and put it in the extra for Intent
                i.putExtra(EXTRA_NAME, tasks.get(pos));
                startActivity(i);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
//                //if left to right sweep event on screen
//                if (x1 < x2) {
//                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
//                }
//                // if right to left sweep event on screen
//                if (x1 > x2) {
//                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
//                }
//                // if UP to Down sweep event on screen
//                if (y1 < y2) {
//                Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
//                }
                //if Down to UP sweep event on screen
                if (y1 > y2) {
                Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, TargetActivity.class);
                startActivity(i);
                }
                break;
            }
        }
        return false;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.check_1:
                if (checked)
                    history.add(tasks.remove(0));
                break;
            case R.id.check_2:
                if (checked)
                    history.add(tasks.remove(1));
                break;
        }
    }

    private void initQuestionBank() {
        questionBank = new ArrayList<>();
        questionBank.add(new Question(getString(R.string.shay_question), true));
        questionBank.add(new Question(getString(R.string.kyle_question), false));
        questionBank.add(new Question(getString(R.string.evan_question), true));
        questionBank.add(new Question(getString(R.string.dog_question), true));
        questionBank.add(new Question(getString(R.string.jake_paul_question), true));
        questionBank.add(new Question(getString(R.string.ricegum_question), false));
        questionBank.add(new Question(getString(R.string.hero_question), false));
        questionBank.add(new Question(getString(R.string.legend_question), true));
    }

    private void initTaskList() {
        tasks = new ArrayList<>();
        tasks.add(new Tasks("Do hw", "do hw by five", "Nov 8", "busy"));
        tasks.add(new Tasks("Do dishes", "do dishes by six", "Nov 20", "available"));
        tasks.add(new Tasks("hehehe", "midnight", "Nov 9", "available"));
    }

    private void initCheckBoxList() {
        checkBoxes = new ArrayList<>();
        checkBoxes.add((CheckBox)findViewById(R.id.check_1));
        checkBoxes.add((CheckBox)findViewById(R.id.check_2));
    }

    private void initHistoryList() {
        history = new ArrayList<>();
    }

    private void wireWidget() {
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        nextButton = (Button) findViewById(R.id.button_next);
        questionText = (TextView) findViewById(R.id.text_question);
        finishButton = (Button) findViewById(R.id.button_finish);
        newTask = (TextView) findViewById(R.id.new_task);
        upArrow = (ImageView) findViewById(R.id.up_arrow);
        taskList = (ListView) findViewById(R.id.task_list);
        displayTaskInfo = (Button) findViewById(R.id.task_info);
        check1 = (CheckBox) findViewById(R.id.check_1);
        check2 = (CheckBox) findViewById(R.id.check_2);
    }

    private void setListeners() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        finishButton.setOnClickListener(this);
        displayTaskInfo.setOnClickListener(this);
        check1.setOnClickListener(this);
        check2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next:
                advanceToNextQuestion();
                if (questionNumber < questionBank.size()) {
                    falseButton.setEnabled(true); //re enable button
                    trueButton.setEnabled(true);
                } else
                    finishButton.setVisibility(View.VISIBLE);
                break;
            case R.id.button_true:
                checkAnswer(true);
                falseButton.setEnabled(false); //disable button
                trueButton.setEnabled(false);
                break;
            case R.id.button_false:
                checkAnswer(false);
                falseButton.setEnabled(false); //disable button
                trueButton.setEnabled(false);
                break;
            case R.id.button_finish:
                finishTest();
                break;
            case R.id.task_info:
                startActivity(new Intent(MainActivity.this, TaskActivity.class).putExtra(EXTRA_NAME, tasks.get(0)));
                break;
        }
    }

    private void checkAnswer(boolean answer) {
        finishButton.setVisibility(View.GONE);
        if (questionBank.get(questionNumber).checkAnswer(answer)) {
            Toast.makeText(this, "WOWZA!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Try again bud :(", Toast.LENGTH_SHORT).show();
            score--;
        }
    }

    private void finishTest() {
        Intent i = new Intent(MainActivity.this, TargetActivity.class);
        i.putExtra("score", score);
        startActivity(i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSavedInstanceState: method fired");
        outState.putInt("question we're on", questionNumber); //store the current question number
    }

//to prevent resetting during rotation, we use SavedInstanceState

    //Android lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: method fired");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: method fired");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: method fired");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: method fired");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: method fired");
    }

    private void advanceToNextQuestion() {
        questionNumber++;
        if (questionNumber < questionBank.size())
            questionText.setText(questionBank.get(questionNumber).getQuestionText()); //if the question is not the last, displays next question
        else {
            questionNumber++;
            falseButton.setEnabled(false); //disable button
            trueButton.setEnabled(false);
            finishButton.setVisibility(View.GONE);
        }
    }

}

