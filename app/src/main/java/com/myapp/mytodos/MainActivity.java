package com.myapp.mytodos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding onClickListener to manage tasks-button that launches new tasks activity
        Button manageTasksBtn = findViewById(R.id.manageTasksBtn);
        manageTasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), TasksActivity.class);
                startActivity(startIntent);
            }
        });

        //adding onClickListener to info-button that launches new info activity
        Button appInfoBtn = findViewById(R.id.appInfoBtn);
        appInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(startIntent);
            }
        });



    }
}
