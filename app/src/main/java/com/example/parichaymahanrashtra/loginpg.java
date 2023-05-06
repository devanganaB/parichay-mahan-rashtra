package com.example.parichaymahanrashtra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.nfc.Tag;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;

public class loginpg extends AppCompatActivity {

        private Button login;
        private TextView forgot;
        private TextView signup;
        private EditText email;
        private EditText password;
        private ImageButton image;
       // private FirebaseAuth auth;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        boolean isLoggedIn= prefs.getBoolean("isLoggedIn", false);

        if(isLoggedIn){
            startActivity(new Intent(getApplicationContext(),nav_home.class));
                    finish();
            return;
        }
        setContentView(R.layout.activity_loginpg);
        login=(Button) findViewById(R.id.button5);
        forgot=(TextView) findViewById(R.id.textView13);
        signup=(TextView) findViewById(R.id.textView14);
        email=(EditText)findViewById(R.id.editTextTextEmailAddress);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        image=(ImageButton)findViewById(R.id.imageButton);
        DB=new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),nav_home.class);
//                startActivity(intent);
                String mail=email.getText().toString();
                String pword=password.getText().toString();

                if(mail.equals("") || pword.equals(""))
                {
                    Toast.makeText(loginpg.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkmailpass=DB.checkemailpassword(mail,pword);
                    if(checkmailpass==true){
                        SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                        editor.putString("email", mail);
                        editor.putString("password", pword);
                        editor.putBoolean("isLoggedIn", true);
//                        editor.putBoolean("name",)
                        editor.apply();

                        Toast.makeText(loginpg.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),nav_home.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Explore Maharashtra!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(loginpg.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OTP.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registerpg.class);
                startActivity(intent);
            }
        });

//        image.setOnClickListener {
//            if(showHideBtn.text.toString().equals("Show")){
//                pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                showHideBtn.text = "Hide"
//            } else{
//                pwd.transformationMethod = PasswordTransformationMethod.getInstance()
//                showHideBtn.text = "Show"
//            }
//        }
        image .setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });}}



//    public void loginuser(String mail, String pword) {
//        auth.signInWithEmailAndPassword(mail,pword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        //Log.d(Tag,"SIGNINWITHEMAIL:SUCCESS");
//                        FirebaseUser User= auth.getCurrentUser();
//                        Toast.makeText(loginpg.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(loginpg.this,OTP.class);
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(), "Explore Maharashtra!!", Toast.LENGTH_SHORT).show();
//
//                    }
//                    else
//                    {
//                        Toast.makeText(loginpg.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
//                    }
//                    }
//                });
////        Listener(new OnSuccessListener<AuthResult>() {
////            @Override
////            public void onSuccess(AuthResult authResult) {
////                Toast.makeText(loginpg.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(loginpg.this,OTP.class);
////                startActivity(intent);
////                Toast.makeText(getApplicationContext(), "Explore Maharashtra!!", Toast.LENGTH_SHORT).show();
////                finish();
////
////            }
////        });
//    }
//
//
//}