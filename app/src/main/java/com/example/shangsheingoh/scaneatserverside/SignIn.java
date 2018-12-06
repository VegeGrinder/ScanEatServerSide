package com.example.shangsheingoh.scaneatserverside;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shangsheingoh.scaneatserverside.Common.Common;
import com.example.shangsheingoh.scaneatserverside.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText editPhone, editPassword;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editPassword = (EditText)findViewById(R.id.editPassword);
        editPhone = (EditText)findViewById(R.id.editPhone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(editPhone.getText().toString(),editPassword.getText().toString());
            }
        });
    }

    private void signInUser(String phone, String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please wait...");
        mDialog.show();

        final String localPhone = phone;
        final String localPassword = password;

//        users.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.child(localPhone).exists()){
//                    mDialog.dismiss();
//                    User user = dataSnapshot.child(localPhone).getValue(User.class);
//                    user.setPhone(localPhone);
//                    if(Boolean.parseBoolean(user.getIsStaff())){
//                        if(user.getPassword().equals(localPassword)){
//                            Intent login = new Intent(SignIn.this,Home.class);
//                            Common.currentUser = user;
//                            Log.d("name",Common.currentUser.getName());
//                            Log.d("phone",Common.currentUser.getPhone());
//                            Log.d("staff",Common.currentUser.getIsStaff());
//                            startActivity(login);
//                            finish();
//                        }
//                        else{
//                            Toast.makeText(SignIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                    else{
//                        Toast.makeText(SignIn.this, "Please login with staff account", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    mDialog.dismiss();
//                    Toast.makeText(SignIn.this, "User does not exist in database", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
