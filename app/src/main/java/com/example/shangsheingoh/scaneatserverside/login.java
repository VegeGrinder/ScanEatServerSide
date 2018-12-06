package com.example.shangsheingoh.scaneatserverside;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangsheingoh.scaneatserverside.Common.Common;
import com.example.shangsheingoh.scaneatserverside.Model.User;
import com.example.shangsheingoh.scaneatserverside.Model.UserProfile;
import com.example.shangsheingoh.scaneatserverside.Common.Common;
import com.example.shangsheingoh.scaneatserverside.Model.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private EditText Email, Password;
    private Button Login;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etpw);
        Login = findViewById(R.id.loginbttn);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(login.this);

        //see whether after register will go to main or second activity
//        if (user != null) {
//            finish();
//            startActivity(new Intent(login.this, Home.class));
//        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });


    }


    private void validate(String userEmail, String userPassword) {
        progressDialog.setMessage("Please wait for a moment while you are verified!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    final DatabaseReference databaseReference = firebaseDatabase.getReference("User Information").child(firebaseAuth.getUid());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                progressDialog.dismiss();
//                                UserProfile user =dataSnapshot.getValue(UserProfile.class);
//                                Common.currentUser =user;
                                Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                login.this.finish();
                                startActivity(new Intent(login.this, Home.class));

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(login.this, "Please enter a valid email address and password", Toast.LENGTH_SHORT).show();
                                login.this.finish();
                                startActivity(new Intent(login.this, Home.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(login.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}



