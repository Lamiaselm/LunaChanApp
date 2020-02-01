package com.example.lunachan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import static com.example.lunachan.RegisterActivity.Md5;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private    EditText email;
    private    EditText pswd;
    private    Button login ;
    private Button register;
    private ProgressDialog progressBar;
    public static   String UID;
    private boolean exist=false;

    //FIREBASE
   private FirebaseDatabase firebaseDatabase;
   private    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar=new ProgressDialog(this);
        email=(EditText)findViewById(R.id.email);
        pswd=(EditText)findViewById(R.id.pswd);
        login=(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.register);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail= email.getText().toString();
                final String mdp =pswd.getText().toString();


               User user  = new User(mail,mdp);
                System.out.println(mail+mdp);
                progressBar.setMessage("Login user please wait ...");
                progressBar.show();



                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                            for(DataSnapshot readData: dataSnapshot.getChildren()){
                               // System.out.println(readData.child("mdp"));
                                if(mail.isEmpty()||mdp.isEmpty())
                                {
                                    Toast.makeText(LoginActivity.this, "Please enter all the information required ;.;", Toast.LENGTH_SHORT).show();


                                }
                                else {


                                if ((readData.child("mdp").getValue().equals(Md5(mdp)))&&(readData.child("mail").getValue().equals(mail)))
                                {

                                    Intent intent =new Intent(LoginActivity.this,ProfileSama.class);

                                    System.out.println("done");

                                       exist=true;
                                        UID=readData.getKey();
                                    //System.out.println("ID USER = "+UID);

                                    Toast.makeText(LoginActivity.this, "WELCOME T.T", Toast.LENGTH_SHORT).show();

                                    startActivity(intent);
                                }


                                }

                            }
                            if(!exist)

                            {
                                Toast.makeText(LoginActivity.this, "Please Sign up , the account doesn't exist ! Or that your password is correct !  ;-;", Toast.LENGTH_SHORT).show();

                            }


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                       Toast.makeText(LoginActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }




}




















