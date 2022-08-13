package com.example.TestApp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class home_activity extends AppCompatActivity {

    private Button loginHomeButton, registerHomeButton;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        database = DBHelper.getInstance(this);
        database.setAllquestions();
        loginHomeButton = (Button) findViewById(R.id.loginHomeButton);
        registerHomeButton = (Button) findViewById(R.id.registerHomeButton);

        loginHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin_page();
            }
        });

        registerHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister_page();
            }
        });

    }

    public void openRegister_page(){
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);
    }

    public void openLogin_page(){
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }
}