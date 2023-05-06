package com.example.parichaymahanrashtra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity2 extends AppCompatActivity {







        public Button button;
        public Button reg;
        public Button skip;
        DBHelper DB;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            button = (Button) findViewById(R.id.login);
            reg = (Button) findViewById(R.id.button3);
            skip = (Button) findViewById(R.id.button4);

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Registerpg.class);
                    startActivity(intent);

                }
            });
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),nav_home.class);
                    startActivity(intent);

                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), loginpg.class);
                    startActivity(intent);

                }
            });
        }
    }



