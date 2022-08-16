package com.example.TestApp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class reading extends AppCompatActivity {
    public static final String SEND_USERNAME = "LOGOUT";
    DBHelper DB;
    Button submitTheAnswer;
    EditText answer;
    TextView questionContent;
    int[] questions = {46,47,48,41,52};
    int currentQuestion = 0;
    int quizAttemptNumber = 0;
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading);

        Intent intent = getIntent();
        String userInfo = intent.getStringExtra(SEND_USERNAME);
        DB = DBHelper.getInstance(this);
        submitTheAnswer = findViewById(R.id.submitAnswerButton);
        answer = findViewById(R.id.answerContent);

        randomiseQuestions();
        setQuestionContent(0, questions[0]);

        //prints a message popup to the user
        StringBuffer buffer = new StringBuffer();
        buffer.append("For the answer type 1, 2, 3 or 4. Do not type out the answer itself, just type the corresponding number");
        AlertDialog.Builder builder = new AlertDialog.Builder(reading.this);
        builder.setCancelable(true);
        builder.setTitle("Instructions");
        builder.setMessage(buffer.toString());
        builder.show();

        submitTheAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizAttemptNumber = DB.getNewQuizAttemptNumber(userInfo)+1;
                if(currentQuestion<4){
                    setQuestionContent(currentQuestion+1,questions[currentQuestion+1]);
                    currentQuestion++;
                    String answerTXT = answer.getText().toString();
                    updateScore(questions[currentQuestion-1], answerTXT);
                    answer.getText().clear();
                }else{
                    currentQuestion++;
                    String answerTXT = answer.getText().toString();
                    updateScore(questions[currentQuestion-1], answerTXT);
                    Toast t = Toast.makeText(reading.this, "You scored "+score+" out of 5", Toast.LENGTH_LONG);
                    t.show();
                    DB.updateQuizAttempt(quizAttemptNumber, userInfo, "Reading Skills", score);
                    DB.addTotalScore(userInfo, score);
                    openHome_page(userInfo);
                }
            }
        });


    }

    protected void updateScore(int question, String UserAnswer){
        Cursor cursor = DB.getSelectQuiz(question);
        int CorrectAnswerInt;
        cursor.moveToNext();
        CorrectAnswerInt = cursor.getInt(3);
        String CorrectAnswer = String.valueOf(CorrectAnswerInt);
        if(CorrectAnswer.equals(UserAnswer)){
            score++;
        }else{
            Log.e("e","incorrect: "+question+" "+UserAnswer+" "+CorrectAnswer);
        }
    }


    protected void setQuestionContent(int pageNum, int qNum){
        questionContent = (TextView)findViewById(R.id.questionContent);
        Cursor cursor = DB.getSelectQuiz(qNum);
        String questionDetails ="";
        cursor.moveToNext();
        questionDetails = cursor.getString(2);
        questionContent.setText(pageNum+1+") "+questionDetails);
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
            }
            questions[i] = qnum;
        }
    }

    public int randomNumber(){
        int value = (int)Math.round(Math.random()*20+40);
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
