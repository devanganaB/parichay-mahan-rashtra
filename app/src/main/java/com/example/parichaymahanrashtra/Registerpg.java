package com.example.parichaymahanrashtra;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class Registerpg extends AppCompatActivity {

        private Button reg;
        private EditText name;
        //private EditText last;
        private EditText email;
        private EditText pass;
        private EditText conf;
        private EditText phone;
        DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpg);

        reg=(Button) findViewById(R.id.button6);
        name= findViewById(R.id.editTextTextPersonName);
       // last=findViewById(R.id.editTextTextPersonName2);
        email=findViewById(R.id.editTextTextEmailAddress2);
        pass=findViewById(R.id.editTextTextPassword2);
        conf=findViewById(R.id.editTextTextPassword3);
        phone =findViewById(R.id.editTextPhone2);
        DB = new DBHelper(this);

        //auth=FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname=name.getText().toString();
                //String lname=last.getText().toString();
                String mail=email.getText().toString();
                String pword=pass.getText().toString();
                String cpass=conf.getText().toString();
                String contact=phone.getText().toString();

                if(fname.equals("")||mail.equals("")||pword.equals("")||cpass.equals("")||contact.equals(""))
                {
                    Toast.makeText(Registerpg.this,"Empty Credentials!!",Toast.LENGTH_SHORT).show();
                }
                else if(pword.length()<8)
                {
                    Toast.makeText(Registerpg.this,"Password too Short!!",Toast.LENGTH_SHORT).show();
                }

                else if(!TextUtils.isDigitsOnly(contact))
                {
                    Toast.makeText(Registerpg.this,"Enter number not character!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pword.equals(cpass)){
                        Boolean checkmail =DB.checkemail(mail);
                        if(checkmail==false)
                        {
                            Boolean insert =DB.insertData(mail,pword,fname,contact);
                            if(insert==true){
                                Toast.makeText(Registerpg.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),nav_home.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Explore Maharashtra!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Registerpg.this, "Registration Failed!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Registerpg.this, "User Already exists..", Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        Toast.makeText(Registerpg.this,"Check Password again!!",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }}

//    public void onStart(){
//        super.onStart();
//        FirebaseUser currentuser=auth.getCurrentUser();
//        if(currentuser != null){
//            startActivity(new Intent(getApplicationContext(),nav_home.class));
//        }
//    }

//    private void registerUser(String first, String last) {
//    auth.createUserWithEmailAndPassword(
//            first, last).addOnCompleteListener(Registerpg.this, new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if(task.isSuccessful())
//            {
//                Toast.makeText(Registerpg.this,"Registered Successfully!!",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(),nav_home.class);
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"Explore MAHARASHTRA!!",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(Registerpg.this,"Registeration Unsuccessful!!",Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
//    }
//}