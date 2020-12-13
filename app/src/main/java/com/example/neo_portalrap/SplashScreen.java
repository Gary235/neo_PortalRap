package com.example.neo_portalrap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            //Write whatever to want to do after delay specified (1 sec)
            Intent intent;
            intent = new Intent(getApplicationContext() ,MainActivity.class);
            startActivity(intent);
            finish();
            Log.d("Handler", "Running Handler");
        }, 800);


    }
}