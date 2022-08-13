package com.example.TestApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class main_quiz_page extends AppCompatActivity {
public static final String SEND_USERNAME = "LOGOUT";

    DBHelper DB;
    Button mathsButton,thinkingButton,readingButton,pastScores,tutorial,logoutButton;
    public String userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_quiz_page);
        String logout = "LOGOUT";

        Intent intent = getIntent();
        userInfo = intent.getStringExtra(SEND_USERNAME);
        if(userInfo.equals(logout) || userInfo == null){ //takes user back to home page if they are not logged in
            openHomeActivity_page();
            System.out.println("ERRORs no login details");
        }

        TextView userView = (TextView)findViewById(R.id.textTitleHello);
        userView.setText("Hello and welcome "+userInfo);
        DB = DBHelper.getInstance(this);

        mathsButton = findViewById(R.id.maths);
        thinkingButton =findViewById(R.id.thinking);
        readingButton = findViewById(R.id.reading);
        pastScores = findViewById(R.id.pastScores);
        tutorial = findViewById(R.id.tutorial);
        logoutButton = findViewById(R.id.logOutOfUserAccount);

        mathsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMath_page();
            }
        });

        thinkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openThink_page();
            }
        });

        readingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRead_page();
            }
        });

        pastScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTutorial_page();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main_quiz_page.this, userInfo+", you have "+DB.getPointsTotal(userInfo)+" points total.", Toast.LENGTH_SHORT).show();
                openHomeActivity_page();
            }
        });
    }

    public void openTutorial_page(){
        Intent intent = new Intent(this, tutorial.class);
        startActivity(intent);
    }

    public void openHomeActivity_page(){
        Intent intent = new Intent(this, home_activity.class);
        startActivity(intent);
    }

    public void openMath_page(){
        Intent intent = new Intent(this, mathematical_reasoning.class);
        startActivity(intent);
    }

    public void openThink_page(){
        Intent intent = new Intent(this, thinking_skills.class);
        startActivity(intent);
    }

    public void openRead_page(){
        Intent intent = new Intent(this, reading.class);
        startActivity(intent);
    }
}
