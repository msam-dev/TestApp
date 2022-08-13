package com.example.TestApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        insertQuiz(11, "Thinking Skills", "What colour is the sky", 1);
        insertQuiz(12, "Thinking Skills", "Who is the PM of Australia", 2);
        insertQuiz(13, "Thinking Skills", "How many wheels does a bicycle have", 3);
        insertQuiz(14, "Thinking Skills", "What is a String in CS", 4);
        insertQuiz(15, "Thinking Skills", "What is the capital of Australia", 1);
        insertQuiz(16, "Thinking Skills", "What is the queen of the UKs first name", 2);
        insertQuiz(17, "Thinking Skills", "What is the formula for water", 3);
        insertQuiz(18, "Thinking Skills", "What colour is a ripe banana", 4);
        insertQuiz(19, "Thinking Skills", "What animal is a russian blue", 1);
        insertQuiz(20, "Thinking Skills", "How many millions in a billion", 2);

        insertQuiz(11, "Reading", "What does the word Triangle mean ", 1);
        insertQuiz(12, "Reading", "What does the word Expand mean", 2);
        insertQuiz(13, "Reading", "What does the word Piano mean", 3);
        insertQuiz(14, "Reading", "What does the word Soldier mean", 4);
        insertQuiz(15, "Reading", "What does the word Democracy mean", 1);
        insertQuiz(16, "Reading", "What is the correct spelling of the fruit", 2);
        insertQuiz(17, "Reading", "What is the correct spelling of the verb", 3);
        insertQuiz(18, "Reading", "What is the correct spelling of the country", 4);
        insertQuiz(19, "Reading", "What is the correct spelling of the flying vehicle", 1);
        insertQuiz(20, "Reading", "What is the correct spelling of the country", 2);

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

    public Cursor getQuizResults(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Quizresults", null);
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
}
