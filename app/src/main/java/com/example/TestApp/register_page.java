package com.example.TestApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register_page extends AppCompatActivity {

    DBHelper DB;
    EditText userName, email, password;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        userName = findViewById(R.id.enterUsernameRegister);
        email = findViewById(R.id.enterEmailRegister);
        password = findViewById(R.id.enterPasswordRegister);
        registerButton = findViewById(R.id.userRegisterButton);
        DB = DBHelper.getInstance(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameTXT = userName.getText().toString();
                String emailTXT = email.getText().toString();
                String passwordTXT = password.getText().toString();

                System.out.println(emailTXT + userNameTXT + passwordTXT);
                if(userNameTXT.isEmpty()|| emailTXT.isEmpty() || passwordTXT.isEmpty()){
                    Toast.makeText(register_page.this, "ERROR enter all information", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean emailTest = DB.checkEmail(emailTXT);
                    Boolean userTest = DB.checkUserName(userNameTXT);

                    if(!userTest){
                        Toast.makeText(register_page.this, "ERROR Username already in use ", Toast.LENGTH_SHORT).show();
                    }else if(!emailTest){
                        Toast.makeText(register_page.this, "ERROR Email already in use ", Toast.LENGTH_SHORT).show();
                    }else{
                        DB.insertUser(userNameTXT,emailTXT,passwordTXT);
                        Toast.makeText(register_page.this, "Successful ", Toast.LENGTH_SHORT).show();
                        openMainQuiz_page();
                    }
                }


            }
        });
    }

    public void openMainQuiz_page(){
        Intent intent = new Intent(this, main_quiz_page.class);
        intent.putExtra(main_quiz_page.SEND_USERNAME, userName.getText().toString());
        startActivity(intent);
    }
}
