package com.example.parichaymahanrashtra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {

    public Button login,getotp;

    public EditText phone,otp1,otp2,otp3,otp4,otp5,otp6;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getotp =(Button) findViewById(R.id.button2);
        login=(Button)findViewById(R.id.button0);
        phone =(EditText)findViewById(R.id.editTextPhone);
        otp1 =(EditText)findViewById(R.id.otp1);
        otp2 =(EditText)findViewById(R.id.otp2);
        otp3 =(EditText)findViewById(R.id.otp3);
        otp4 =(EditText)findViewById(R.id.otp4);
        otp5 =(EditText)findViewById(R.id.otp5);
        otp6 =(EditText)findViewById(R.id.otp6);
        DB=new DBHelper(this);
        final String[] backendotp = new String[1];
        final ProgressBar pb=findViewById(R.id.pb1);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact=phone.getText().toString();
                if(!contact.isEmpty())
                {
                    if(contact.length()==10)
                    {
                        pb.setVisibility(View.VISIBLE);
                        getotp.setVisibility(View.INVISIBLE);
                        Boolean checkphone=DB.checkphone(contact);
                        if(checkphone==true)
                        {
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+91" + contact,
                                    60,
                                    TimeUnit.SECONDS,
                                    OTP.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            pb.setVisibility(View.GONE);
                                            getotp.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            pb.setVisibility(View.GONE);
                                            getotp.setVisibility(View.VISIBLE);
                                            Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            pb.setVisibility(View.GONE);
                                            login.setVisibility(View.VISIBLE);
                                            backendotp[0] =s;
                                        }
                                    }
                            );
                        }
                        else
                        {
                            Toast.makeText(OTP.this, "Mobile number is not registered..", Toast.LENGTH_SHORT).show();
                        }

//                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                "+91" + contact,
//                                60,
//                                TimeUnit.SECONDS,
//                                OTP.this,
//                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                    @Override
//                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                        pb.setVisibility(View.GONE);
//                                        getotp.setVisibility(View.VISIBLE);
//
//                                    }
//
//                                    @Override
//                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//                                            pb.setVisibility(View.GONE);
//                                            getotp.setVisibility(View.VISIBLE);
//                                        Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                      pb.setVisibility(View.GONE);
//                                        login.setVisibility(View.VISIBLE);
//                                        backendotp[0] =s;
//                                    }
//                                }
//                        );
                    }
                    else
                    {
                        Toast.makeText(OTP.this, "Please Enter correct phone no.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(OTP.this, "Enter mobile number.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String o1=otp1.getText().toString();
                String o2=otp2.getText().toString();
                String o3=otp3.getText().toString();
                String o4=otp4.getText().toString();
                String o5=otp5.getText().toString();
                String o6=otp6.getText().toString();

                if(!o1.isEmpty()&&!o2.isEmpty()&&!o3.isEmpty()&&!o4.isEmpty()&&!o5.isEmpty()&&!o6.isEmpty())
                {
                    String entercode=o1+o2+o3+o4+o5+o6;

                    if(backendotp[0]!=null){
                        pb.setVisibility(View.VISIBLE);
                        login.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential =PhoneAuthProvider.getCredential(backendotp[0],entercode);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pb.setVisibility(View.INVISIBLE);
                                login.setVisibility(View.VISIBLE);

                                if(task.isSuccessful()){
                                    Intent intent=new Intent(getApplicationContext(),nav_home.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(OTP.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else
                    {
                        Toast.makeText(OTP.this, "Please Check your internet Condition", Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(OTP.this, "Verifying OTP", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(OTP.this, "Please Enter all numbers", Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent(getApplicationContext(),nav_home.class);
//              startActivity(intent);
            }
        });
        numberotpmove();

        findViewById(R.id.textView27).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + phone.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        OTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                pb.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                pb.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);
                                Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                pb.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);
                                backendotp[0] =s;
                            }
                        }
                );
            }
        });
    }

    private void numberotpmove() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            if(!s.toString().isEmpty())
            {
                otp2.requestFocus();
            }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().isEmpty())
                {
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().isEmpty())
                {
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().isEmpty())
                {
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().isEmpty())
                {
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }


}