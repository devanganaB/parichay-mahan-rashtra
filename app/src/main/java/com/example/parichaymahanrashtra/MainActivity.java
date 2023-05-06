package com.example.parichaymahanrashtra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        boolean isLoggedIn= prefs.getBoolean("isLoggedIn", false);
        setContentView(R.layout.activity_main);

    }
    public void openActivity(View v)
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(intent);
        // Inside your activity (if you did not enable transitions in your theme)


// Set an exit transition

    }
}