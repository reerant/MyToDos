package com.myapp.mytodos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //sets a short info text
        TextView infoTxtView = (TextView) findViewById(R.id.infoTextView);
        infoTxtView.setText("This is a simple to-do list application.\n\n" +
                "Go to \"Manage tasks\" where tasks are displayed in a list. It is possible to add a new task or to " +
                "delete a task from the list.\n\n" +
                "For further information, please contact:\n" +
                "Phone +358 123456789\n" +
                "Email reeapp@reeapp.com\n" +
                "© ReeApp Ltd.");
    }
}
