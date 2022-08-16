package com.example.TestApp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sInstance;
    private static final String DBNAME = "dataBaseName";
    private static final String DBTABLE = "databaseTable";
    private static final int DBVERSION = 1;

    public static synchronized DBHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Users(username TEXT primary key, email TEXT, password TEXT, pointstotal NUMBER)");
        DB.execSQL("create Table Quiz(quiznumber NUMBER primary key, quiztype TEXT, question TEXT, answer NUMBER)");
        DB.execSQL("create Table Quizresults(resultnumber NUMBER primary key, username TEXT, quiztype TEXT, correctanswers NUMBER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Users");
    }

    public void setAllquestions(){
        deleteQuizes();

        insertQuiz(1, "Mathematical Reasoning", "10+10", 20);
        insertQuiz(2, "Mathematical Reasoning", "20-10", 10);
        insertQuiz(3, "Mathematical Reasoning", "3x3", 9);
        insertQuiz(4, "Mathematical Reasoning", "100/2", 50);
        insertQuiz(5, "Mathematical Reasoning", "15+15", 30);
        insertQuiz(6, "Mathematical Reasoning", "60-20", 40);
        insertQuiz(7, "Mathematical Reasoning", "2x9", 18);
        insertQuiz(8, "Mathematical Reasoning", "8/4", 2);
        insertQuiz(9, "Mathematical Reasoning", "80+5", 85);
        insertQuiz(10, "Mathematical Reasoning", "4x8", 32);
        insertQuiz(11, "Mathematical Reasoning", "17+2", 19);
        insertQuiz(12, "Mathematical Reasoning", "20+90", 110);
        insertQuiz(13, "Mathematical Reasoning", "100/4", 25);
        insertQuiz(14, "Mathematical Reasoning", "1000/100", 10);
        insertQuiz(15, "Mathematical Reasoning", "15+20", 35);
        insertQuiz(16, "Mathematical Reasoning", "7x3", 21);
        insertQuiz(17, "Mathematical Reasoning", "6x6", 36);
        insertQuiz(18, "Mathematical Reasoning", "93+10", 103);
        insertQuiz(19, "Mathematical Reasoning", "16x8", 128);
        insertQuiz(20, "Mathematical Reasoning", "500/20", 25);

        insertQuiz(21, "Thinking Skills", "What colour is the sky", 1);
        insertQuiz(22, "Thinking Skills", "Who is the PM of Australia", 2);
        insertQuiz(23, "Thinking Skills", "How many wheels does a tricycle have", 3);
        insertQuiz(24, "Thinking Skills", "What is a String in CS", 4);
        insertQuiz(25, "Thinking Skills", "What is the capital of Australia", 1);
        insertQuiz(26, "Thinking Skills", "What is the queen of the UKs first name \n 1)Mary  \n 2)Elizabeth  \n 3)Victoria \n 4)Rose", 2);
        insertQuiz(27, "Thinking Skills", "What is the formula for water", 3);
        insertQuiz(28, "Thinking Skills", "What colour is a ripe banana", 4);
        insertQuiz(29, "Thinking Skills", "What animal is a russian blue", 1);
        insertQuiz(30, "Thinking Skills", "How many millions in a billion", 2);

        insertQuiz(31, "Thinking Skills", "What colour is the sky", 1);
        insertQuiz(32, "Thinking Skills", "Who is the PM of Australia", 2);
        insertQuiz(33, "Thinking Skills", "How many wheels does a bicycle have", 3);
        insertQuiz(34, "Thinking Skills", "What is a String in CS", 4);
        insertQuiz(35, "Thinking Skills", "What is the capital of Australia", 1);
        insertQuiz(36, "Thinking Skills", "What is the queen of the UKs first name", 2);
        insertQuiz(37, "Thinking Skills", "What is the formula for water", 3);
        insertQuiz(38, "Thinking Skills", "What colour is a ripe banana", 4);
        insertQuiz(39, "Thinking Skills", "What animal is a russian blue", 1);
        insertQuiz(40, "Thinking Skills", "How many millions in a billion", 2);

        insertQuiz(41, "Reading", "What does the word Triangle mean ", 1);
        insertQuiz(42, "Reading", "What does the word Expand mean", 2);
        insertQuiz(43, "Reading", "What does the word Piano mean", 3);
        insertQuiz(44, "Reading", "What does the word Soldier mean", 4);
        insertQuiz(45, "Reading", "What does the word Democracy mean", 1);
        insertQuiz(46, "Reading", "What is the correct spelling of the fruit", 2);
        insertQuiz(47, "Reading", "What is the correct spelling of the verb", 3);
        insertQuiz(48, "Reading", "What is the correct spelling of the country", 4);
        insertQuiz(49, "Reading", "What is the correct spelling of the flying vehicle", 1);
        insertQuiz(50, "Reading", "What is the correct spelling of the country", 2);
        insertQuiz(51, "Reading", "What does the word Triangle mean ", 1);

        insertQuiz(52, "Reading", "What does the word Expand mean", 2);
        insertQuiz(53, "Reading", "What does the word Piano mean", 3);
        insertQuiz(54, "Reading", "What does the word Soldier mean", 4);
        insertQuiz(55, "Reading", "What does the word Democracy mean", 1);
        insertQuiz(56, "Reading", "What is the correct spelling of the fruit", 2);
        insertQuiz(57, "Reading", "What is the correct spelling of the verb", 3);
        insertQuiz(58, "Reading", "What is the correct spelling of the country", 4);
        insertQuiz(59, "Reading", "What is the correct spelling of the flying vehicle", 1);
        insertQuiz(60, "Reading", "What is the correct spelling of the country", 2);

    }

    public Boolean insertUser(String username, String emailAddress, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", emailAddress);
        contentValues.put("password", password);
        contentValues.put("pointstotal", 0);
        long result = DB.insert("Users", null, contentValues);
        return result != -1;
    }

    public void insertQuiz(int quiznumber, String quiztype, String question, int answer){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quiznumber", quiznumber);
        contentValues.put("quiztype", quiztype);
        contentValues.put("question", question);
        contentValues.put("answer", answer);
        DB.insert("Quiz", null, contentValues);
    }

    public Cursor getUsers(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users", null);
        return cursor;
    }

    public Cursor getQuizes(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Quiz", null);
        return cursor;
    }

    public Cursor getSelectQuiz(int quizNumber){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.query("Quiz",null,"quiznumber="+quizNumber, null,null,null,null);
        return cursor;
    }

    public Cursor getQuizResults(String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Quizresults where username = ?", new String[]{user});
        return cursor;
    }

    public Boolean updateUser(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        //Cursor cursor = DB.rawQuery("Select * from Users where username = ?", new String[]{username});
        long result = DB.update("Users", contentValues, "username=?", new String[]{username});
        return result != -1;
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users where username = ?", new String[]{username});
        return cursor.getCount() <= 0;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users where email = ?", new String[]{email});
        return cursor.getCount() <= 0;
    }

    public int getPointsTotal(String Username){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users where username = ?", new String[]{Username});
        if(cursor.getCount()==0){return -1;}
        else{
            cursor.moveToNext();
            return cursor.getInt(3);
        }
    }

    public Boolean addTotalScore(String username, int value){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users where username = ?", new String[]{username});
        if(cursor.getCount()==0){return false;}
        else{
            cursor.moveToNext();
            ContentValues contentValues = new ContentValues();
            contentValues.put("pointstotal", cursor.getInt(3)+value);
            long result = DB.update("Users", contentValues, "username=?", new String[]{username});
            return result != -1;
        }

    }

    public Boolean checkLoginCredentials(String usernameLogin, String PasswordLogin){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Users where username = ?", new String[]{usernameLogin});
        if(cursor.getCount()==0){return false;}
        else{
            cursor.moveToNext();
            String passwordUser = cursor.getString(2);
            System.out.println(passwordUser+ " "+ PasswordLogin );
            return PasswordLogin.equals(passwordUser);
        }
    }

    public int getNewQuizAttemptNumber(String Username){
        Log.e("s", "landed");
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select MAX(resultNumber) from Quizresults where username = ?", new String[]{Username});
        Log.e("s", "11111111111111111111");
        if(cursor.getCount()==0){return 1;}
        else{
            cursor.moveToNext();
            Log.e("s", "here");
            int value = cursor.getInt(0);
            return value;
        }
    }

    public void updateQuizAttempt(int attemptNumber, String Username, String QuizType, int Score){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("resultnumber", attemptNumber);
        contentValues.put("username", Username);
        contentValues.put("quiztype", QuizType);
        contentValues.put("correctanswers", Score);
        DB.insert("Quizresults", null, contentValues);
        Log.e("e", "sucess update");
    }

    public void deleteQuizes(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("delete from Quiz");
        Log.e("e","Dropped all quizzes");
    }
}
