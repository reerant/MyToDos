package com.myapp.mytodos;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "AddTaskActivity";
    EditText addTaskEditText;
    Button addTaskBtn;
    Button cancelAddTaskBtn;
    List<String> tasks;
    List<String> dates;
    TextView showDate;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    //sets a default date string value which shows on the task list if a date is not picked by the user.
    String selectedDate = "0/0/0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        addTaskEditText = findViewById(R.id.addTaskEditText);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(this);

        //cancelling add action, goes back to tasks activity
        cancelAddTaskBtn = findViewById(R.id.cancelAddTaskBtn);
        cancelAddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TasksActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        showDate = findViewById(R.id.SelectDateTextView);
        //setting on click listener to text view which has a text on saying "Tap to select a deadline date"
        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //shows the date calendar
                Calendar cal = Calendar.getInstance();
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int d = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddTaskActivity.this, android.R.style.Theme_Black, mDateSetListener, y, m, d);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            //sets the date to showDate text view
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                m = m + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + d + "/" + m + "/" + y);
                selectedDate = d + "/" + m + "/" + y;
                showDate.setText(selectedDate);
            }
        };

    }
        @Override
        public void onClick (View view){
            //if user clicks add button and the keyboard is still open,
            // this closes it and the view on the task activity doesn't "bounce"
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            if (view.getId() == R.id.addTaskBtn) {

                String taskEntered = addTaskEditText.getText().toString();
                tasks = FileHelper.readData(this);
                dates = FileHelperDates.readData(this);

                //adds the text that user wrote to tasks
                tasks.add(taskEntered);
                //adds the date that user chose from date calendar, and if no date is chosen the default date string is added
                dates.add(selectedDate);

                //clears the text view and writes task text into the tasks file
                addTaskEditText.setText("");
                FileHelper.writeData(tasks, this);
                //clears the date view and writes date into the dates file
                showDate.setText("");
                FileHelperDates.writeData(dates, this);

                //when adding new task, the screen goes automatically back to the tasks activity
                Intent intent = new Intent(this, TasksActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }}






