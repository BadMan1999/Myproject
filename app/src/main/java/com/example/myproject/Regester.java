package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Regester extends AppCompatActivity {

    String num_Password;


    TextView ButtonLogin;

    EditText ed_Name, ed_Email, ed_Password, ed_Re_Password;
    Button create;
    FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference rootRef;
    String Email, Password, Uid, Name, Re_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);

        ed_Name = findViewById(R.id.ed_Name);
        ed_Email = findViewById(R.id.ed_Email);
        ed_Password = findViewById(R.id.ed_Password);
        ed_Re_Password = findViewById(R.id.ed_Re_Password);

        ButtonLogin = findViewById(R.id.ButtonLogin);
        create = findViewById(R.id.Create);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        rootRef = database.getReference("BMI");


        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regester.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register() {
        Email = ed_Email.getText().toString().trim();
        Password = ed_Password.getText().toString().trim();
        Re_Password = ed_Re_Password.getText().toString().trim();
        Name = ed_Name.getText().toString().trim();
        if (Email.isEmpty()) {
            ed_Email.setError("Please Enter Email");
            ed_Email.requestFocus();
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Password.equals(Re_Password)) {
            if (Password.isEmpty()) {
                ed_Password.setError("Please Enter Password");
                ed_Password.requestFocus();
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }


        }



        Intent intent = new Intent(Regester.this, Complete_Information_Activity.class);
        intent.putExtra("uid",Uid);
        intent.putExtra("name",Name);
        intent.putExtra("email",Email);
        intent.putExtra("password",Password+"");
        startActivity(intent);
    }
}