package com.example.TestApp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mathematical_reasoning extends AppCompatActivity {
    public static final String SEND_USERNAME = "LOGOUT";
    DBHelper DB;
    Button previousQuestion, nextQuestion, submitTheAnswer;
    EditText answer;
    TextView questionContent;
    int[] questions = {2,4,1,9,5};
    int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathematical_reasoning);
        Intent intent = getIntent();
        String userInfo = intent.getStringExtra(SEND_USERNAME);
        DB = DBHelper.getInstance(this);
        setQuestionContent(0, questions[0]);
        previousQuestion = findViewById(R.id.previousQ);
        nextQuestion = findViewById(R.id.nextQ);
        submitTheAnswer = findViewById(R.id.submitAnswerButton);

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(currentQuestion>0){
                   setQuestionContent(currentQuestion-1,questions[currentQuestion-1]);
                   currentQuestion--;
               }
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion<4){
                    setQuestionContent(currentQuestion+1,questions[currentQuestion+1]);
                    currentQuestion++;
                }
            }
        });

        submitTheAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion<4){
                    setQuestionContent(currentQuestion+1,questions[currentQuestion+1]);
                    currentQuestion++;
                }
            }
        });
    }

    protected void setQuestionContent(int pageNum, int qNum){
        questionContent = (TextView)findViewById(R.id.questionContent);
        Cursor cursor = DB.getSelectQuiz(qNum);
        String questionDetails ="";
        cursor.moveToNext();
        questionDetails = cursor.getString(2);
        questionContent.setText("What is  "+questionDetails);
    }

}
 /*randomise question numbers
        for(int i = 0; i<5; i++){
            if(i == 0){
                questions[i] = ThreadLocalRandom.current().nextInt(1,11);
                continue;
            }
            for(int j = 0; j<5; j++){
                int randomNum = ThreadLocalRandom.current().nextInt(1,11);
                if(randomNum == questions[];
            }
        }*/