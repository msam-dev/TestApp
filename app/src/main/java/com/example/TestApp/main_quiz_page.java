package com.example.TestApp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
            Log.e("e","ERROR no login details");
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
                Cursor res = DB.getQuizResults(userInfo);
                if(res.getCount()==0){
                    Toast.makeText(main_quiz_page.this, "No completed tests", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Quiz 7 mathematical reasoning score 5/5
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(res.getInt(0)+") ");
                    buffer.append(res.getString(2)+ " ");
                    buffer.append("-score "+res.getString(3)+"/5"+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(main_quiz_page.this);
                builder.setCancelable(true);
                builder.setTitle("Past Scores");
                builder.setMessage(buffer.toString());
                builder.show();
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
                Toast.makeText(main_quiz_page.this, userInfo+", you have "+DB.getPointsTotal(userInfo)+" points total.", Toast.LENGTH_LONG).show();
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
        intent.putExtra(mathematical_reasoning.SEND_USERNAME, userInfo);
        startActivity(intent);
    }

    public void openThink_page(){
        Intent intent = new Intent(this, thinking_skills.class);
        intent.putExtra(thinking_skills.SEND_USERNAME, userInfo);
        startActivity(intent);
    }

    public void openRead_page(){
        Intent intent = new Intent(this, reading.class);
        intent.putExtra(reading.SEND_USERNAME, userInfo);
        startActivity(intent);
    }
}
