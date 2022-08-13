package com.example.TestApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class login_page extends AppCompatActivity {

    DBHelper DB;
    EditText userName, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        userName = findViewById(R.id.enterUsernameLogin);
        password = findViewById(R.id.enterPasswordLogin);
        loginButton = findViewById(R.id.userLoginButton);
        DB = DBHelper.getInstance(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameTXT = userName.getText().toString();
                String passwordTXT = password.getText().toString();

                if(userNameTXT.isEmpty() || passwordTXT.isEmpty()){
                    Toast.makeText(login_page.this, "ERROR enter all information", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkDetails = DB.checkLoginCredentials(userNameTXT, passwordTXT);

                    if(!checkDetails){
                        Toast.makeText(login_page.this, "ERROR incorrect details", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(login_page.this, "Successful ", Toast.LENGTH_SHORT).show();
                        openMainQuiz_page();
                    }
                }


            }
        });
    }

    public void openMainQuiz_page(){
        Intent intent = new Intent(login_page.this, main_quiz_page.class);
        intent.putExtra(main_quiz_page.SEND_USERNAME, userName.getText().toString());
        startActivity(intent);
    }
}
