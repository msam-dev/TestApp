package com.example.TestApp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class mathematical_reasoning extends AppCompatActivity {
    public static final String SEND_USERNAME = "LOGOUT";
    DBHelper DB;
    Button submitTheAnswer;
    EditText answer;
    TextView questionContent;
    int[] questions = {3,6,2,5,14};
    int currentQuestion = 0;
    int quizAttemptNumber = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("ss","math page created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathematical_reasoning);
        Intent intent = getIntent();
        String userInfo = intent.getStringExtra(SEND_USERNAME);
        DB = DBHelper.getInstance(this);

        submitTheAnswer = findViewById(R.id.submitAnswerButton);
        answer = findViewById(R.id.answerContent);
        randomiseQuestions();
        quizAttemptNumber = 1;
        setQuestionContent(0, questions[0]);
        Log.e("e", Arrays.toString(questions));
        /*        DB.updateQuizAttempt(777700, userInfo, "Mathematical Reasoning", 5);
        Cursor cursors = DB.getQuizResults();
        cursors.moveToNext();
        Log.e("e",cursors.getString(0)+" "+cursors.getString(1) +" "+cursors.getString(2)); */

        submitTheAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("e","submit clicked");
                quizAttemptNumber = DB.getNewQuizAttemptNumber(userInfo)+1;
                Log.e("e",String.valueOf(quizAttemptNumber));

                if(currentQuestion<4){
                    //Log.e("e","zzzzzzzzzzz");
                    setQuestionContent(currentQuestion+1,questions[currentQuestion+1]);
                    currentQuestion++;
                    String answerTXT = answer.getText().toString();
                    updateScore(questions[currentQuestion-1], answerTXT);
                    answer.getText().clear();
                }else{
                    currentQuestion++;
                    String answerTXT = answer.getText().toString();
                    updateScore(questions[currentQuestion-1], answerTXT);
                    Log.e("e","score:" + score);
                    Toast t = Toast.makeText(mathematical_reasoning.this, "You scored "+score+" out of 5", Toast.LENGTH_LONG);
                    t.show();
                    DB.updateQuizAttempt(quizAttemptNumber, userInfo, "Mathematical Reasoning", score);
                    //Log.e("e","5555555555555555");
                    DB.addTotalScore(userInfo, score);
                    openHome_page(userInfo);
                }
            }
        });
    }

    protected void updateScore(int question, String UserAnswer){
        //Log.e("e","xxxxxxxxxxxx");

        Cursor cursor = DB.getSelectQuiz(question);
        int CorrectAnswerInt;
        cursor.moveToNext();
        CorrectAnswerInt = cursor.getInt(3);
        String CorrectAnswer = String.valueOf(CorrectAnswerInt);
        if(CorrectAnswer.equals(UserAnswer)){
            score++;
            Log.e("e","Correct");
        }else{
            Log.e("e","incorrect: "+question+" "+UserAnswer+" "+CorrectAnswer);
        }
    }

    protected void setQuestionContent(int pageNum, int qNum){
        questionContent = (TextView)findViewById(R.id.questionContent);
        Cursor cursor = DB.getSelectQuiz(qNum);
        Log.e("e","qcontent: "+qNum);
        String questionDetails ="";
        cursor.moveToNext();
        questionDetails = cursor.getString(2);
        questionContent.setText(pageNum+1+") What is  "+questionDetails);
    }

    public void openHome_page(String UserInfo){
        Intent intent = new Intent(this, main_quiz_page.class);
        intent.putExtra(main_quiz_page.SEND_USERNAME, UserInfo);
        startActivity(intent);
    }

    public void randomiseQuestions(){
        for(int i = 0; i<5; i++){
            int qnum = randomNumber();
            if(i==0){
                questions[i] = qnum;
            }
            while(arrayContains(qnum,i)){
                qnum = randomNumber();
                Log.e("e",String.valueOf(qnum));
            }
            questions[i] = qnum;
        }
    }

    public int randomNumber(){
        int value = (int)Math.round(Math.random()*20);
        if(value ==0){
            value =randomNumber();
        }
        return value;
    }

    public Boolean arrayContains(int value, int index){
        for(int i =0; i<index; i++){
            if(questions[i] == value){
                return true;
            }
        }
        return false;
    }
}


