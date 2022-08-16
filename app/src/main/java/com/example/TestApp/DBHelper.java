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

        insertQuiz(21, "Thinking Skills", "What colour is the sky \n 1)blue \n 2)yellow \n 3)green \n 4)maroon", 1);
        insertQuiz(22, "Thinking Skills", "Who is the PM of Australia \n 1)Morrison \n 2)Albanese \n 3)Turnbull \n 4)Rudd", 2);
        insertQuiz(23, "Thinking Skills", "How many wheels does a tricycle have \n 1)Four \n 2)One \n 3)Three \n 4)Two", 3);
        insertQuiz(24, "Thinking Skills", "What is a int in CS \n 1)word \n 2)decimal \n 3)letter \n 4)whole number", 4);
        insertQuiz(25, "Thinking Skills", "What is the capital of Australia \n 1)Canberra \n 2)Sydney \n 3)Melbourne \n 4)Perth", 1);
        insertQuiz(26, "Thinking Skills", "What is the queen of the UKs first name \n 1)Mary  \n 2)Elizabeth  \n 3)Victoria \n 4)Rose", 2);
        insertQuiz(27, "Thinking Skills", "What is the formula for water \n 1)CO2 \n 2)HO2 \n 3)H2O \n 4)H2O2", 3);
        insertQuiz(28, "Thinking Skills", "What colour is a ripe banana \n 1)blue \n 2)red \n 3)pink \n 4)yellow", 4);
        insertQuiz(29, "Thinking Skills", "What animal is a russian blue \n 1)cat \n 2)dog \n 3)sheep \n 4)bear", 1);
        insertQuiz(30, "Thinking Skills", "How many millions in a billion \n 1)10 \n 2)100 \n 3)1000 \n 4)10000", 3);
        insertQuiz(31, "Thinking Skills", "What colour pigment was made from snail shells \n 1)green \n 2)yellow \n 3)purple \n 4)blue", 3);
        insertQuiz(32, "Thinking Skills", "Who is the US President \n 1)Obama \n 2)Bush \n 3)Trump \n 4)Biden", 4);
        insertQuiz(33, "Thinking Skills", "How many wheels does a bicycle have \n 1)Three \n 2)Two \n 3)Four \n 4)One", 3);
        insertQuiz(34, "Thinking Skills", "How old are the pyramids of Giza \n 1)500yr \n 2)20000yr \n 3)1000yr \n 4)4500yr", 4);
        insertQuiz(35, "Thinking Skills", "What is the capital of Japan \n 1)Tokyo \n 2)Osaka \n 3)Nagasaki \n 4)Saga", 1);
        insertQuiz(36, "Thinking Skills", "What is the capital of China \n 1)Shanghai \n 2)Beijing \n 3)Shenzhen \n 4)Wuhan", 2);
        insertQuiz(37, "Thinking Skills", "What is the formula for Carbon dioxide \n 1)CO \n 2)C2O \n 3)CO2 \n 4)C202", 3);
        insertQuiz(38, "Thinking Skills", "How many sides does a heptagon have \n 1)Eleven \n 2)Seven \n 3)Eight \n 4)Nine", 2);
        insertQuiz(39, "Thinking Skills", "What animal is a corgi \n 1)dog \n 2)bird \n 3)fish \n 4)cat", 1);
        insertQuiz(40, "Thinking Skills", "How many sides does a pentagon have \n 1)Eight \n 2)Seven \n 3)Six \n 4)Five", 4);

        insertQuiz(41, "Reading", "What does the word grotesque mean \n 1)very ugly \n 2)very beautiful \n 3)very large \n 4)very smart", 1);
        insertQuiz(42, "Reading", "What does the word Expand mean \n 1)Decrease in size \n 2)Increase in size \n 3)Turn around \n 4)Move left", 2);
        insertQuiz(43, "Reading", "What does the word Loud mean \n 1)Low Volume \n 2)Tall in stature \n 3)High Volume \n 4)cat", 3);
        insertQuiz(44, "Reading", "What does the word opaque mean \n 1)transparent \n 2)shiny \n 3)special \n 4)non-transparent", 4);
        insertQuiz(45, "Reading", "What does the word Shrink mean \n 1)Decrease in size \n 2)Increase in size \n 3)Turn around \n 4)Move left", 1);
        insertQuiz(46, "Reading", "What is the correct spelling of the fruit \n 1)Bana \n 2)Banana \n 3)Bannana \n 4)Banaana", 2);
        insertQuiz(47, "Reading", "What is the correct spelling of the country \n 1)Irane \n 2)Oran \n 3)Iran \n 4)Eran", 3);
        insertQuiz(48, "Reading", "What is the correct spelling of the animal \n 1)hippopotamous \n 2)hippopotamuse \n 3)hippapotamouse \n 4)hippopotamus", 4);
        insertQuiz(49, "Reading", "What is the correct spelling of the flying vehicle \n 1)Plane \n 2)Plain \n 3)Plein \n 4)Plaine", 1);
        insertQuiz(50, "Reading", "What is the correct spelling of the country \n 1)Australiar \n 2)Australia \n 3)Autsralia \n 4)Austrelia", 2);
        insertQuiz(51, "Reading", "What does the word Triangle mean \n 1)3 sided shape \n 2)2 sided shape \n 3)8 sided shape \n 4)4 sided shape", 1);
        insertQuiz(52, "Reading", "What does the word Stupendous mean \n 1)very stupid \n 2)very impressive \n 3)very small \n 4)very awfull", 2);
        insertQuiz(53, "Reading", "What does the word Rapid mean \n 1)very orderly \n 2)very slow \n 3)very fast \n 4)very good", 3);
        insertQuiz(54, "Reading", "What does the word Scrawny mean \n 1)very fat \n 2)very athletic \n 3)very strong \n 4)very skinny", 4);
        insertQuiz(55, "Reading", "What does the word Error mean \n 1)a mistake \n 2)a success \n 3)happy \n 4)very lucky", 1);
        insertQuiz(56, "Reading", "What is the correct spelling of the vegetable \n 1)brockoli \n 2)broccoli \n 3)brokkoli \n 4)broccoly", 2);
        insertQuiz(57, "Reading", "What is the correct spelling of the vegetable \n 1)bock choi \n 2)bock choy \n 3)bok choy \n 4)bok choi", 3);
        insertQuiz(58, "Reading", "What is the correct spelling of the country \n 1)Zimbabwee \n 2)Zimabwe \n 3)Zimmbabway \n 4)Zimbabwe", 4);
        insertQuiz(59, "Reading", "What is the correct spelling of the vehicle \n 1)Bicycle \n 2)Bycycle \n 3)Bicicle \n 4)Bicycel", 1);
        insertQuiz(60, "Reading", "What is the correct spelling of the country \n 1)Madakascar \n 2)Madagascar \n 3)Madegascar \n 4)Madigascar", 2);

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
