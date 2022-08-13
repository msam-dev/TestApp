package com.example.TestApp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        iv_image = (ImageView) findViewById(R.id.iv_image);
        iv_image.animate().rotationBy(1080f).setDuration(5000).withEndAction(new Runnable() {
            @Override
            public void run() {
                iv_image.animate().scaleXBy(-1f).scaleYBy(-1f).setDuration(200).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        openHome_page();
                    }
                });
            }
        });
    }

    public void openHome_page(){
        Intent intent = new Intent(this, home_activity.class);
        startActivity(intent);
    }
}