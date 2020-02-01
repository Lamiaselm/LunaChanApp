package com.example.lunachan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {

    private EditText new_email;
    private EditText new_pswd;
    private EditText confirm_pswd;
    private EditText full_name;
    private EditText phone;
    private EditText country;
    private EditText date_birth;
    private String Nfull_name;
    private String Nphone;
    private String Ncountry;
    private String Ndate_birth;
    private boolean exist=false;
    private Button register_submit;
    private String Nmail;
    private String Npswd,Ncpswd;
    private TextView go_to_loginT;
private ProgressDialog progressDialog;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(this);
        new_email=(EditText)findViewById(R.id.new_email);
        new_pswd=(EditText)findViewById(R.id.new_pswd);
        confirm_pswd=(EditText)findViewById(R.id.confirm_pswd);
        go_to_loginT=(TextView)findViewById(R.id.go_to_loginT);
        full_name=(EditText)findViewById(R.id.full_name);
        phone=(EditText)findViewById(R.id.phone);
        country=(EditText)findViewById(R.id.country);
        date_birth=(EditText) findViewById(R.id.date_birth);
        register_submit=(Button) findViewById(R.id.register_submit);



        //<---------------------------FireBase Connexion ---------------------------------------------->
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
        register_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Nmail=new_email.getText().toString().trim();
                Npswd=new_pswd.getText().toString().trim();
                Ncpswd=confirm_pswd.getText().toString().trim();
                Nfull_name=full_name.getText().toString().trim();
                Nphone=phone.getText().toString().trim();
                Ncountry=confirm_pswd.getText().toString().trim();
                Ndate_birth=date_birth.getText().toString().trim();
                progressDialog.setMessage("Register User , please wait ...");
                progressDialog.show();

                System.out.println(Nmail+Npswd+Ncpswd);

                if(Nmail.isEmpty() || Npswd.isEmpty() || Ncpswd.isEmpty() || Ncountry.isEmpty() || Ndate_birth.isEmpty() || Nfull_name.isEmpty() || Nphone.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all the information required ! -.-", Toast.LENGTH_SHORT).show();

                }else
                if((!(Npswd.equals(Ncpswd)))|| UserExists(Nmail,Nfull_name))
                {
                    if(UserExists(Nmail,Nfull_name)){
                        System.out.println("el user existe");

                        Toast.makeText(RegisterActivity.this, "The Email already exists ! try with another Email to creat your account &.&", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(RegisterActivity.this, "Please confirm your password correctly! -.-", Toast.LENGTH_SHORT).show();
                    }
                }


                else {

                  InsertData();
                    Toast.makeText(RegisterActivity.this, "You have been registered successfully , go to login now ! *-* ", Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent2);
                }

            }
        });
        go_to_loginT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public boolean UserExists(final String  mail , final String full_name)

    {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot readData: dataSnapshot.getChildren()){
                    System.out.println(readData.child("mail"));

                    if (readData.child("mail").getValue().equals(mail) || readData.child("full_name").getValue().equals(full_name))
                    {   exist=true;
                       // System.out.println("el user existe");
                        System.out.println(exist);

                    }

                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RegisterActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return exist;
    }
    private void InsertData() {
        Nmail=new_email.getText().toString();
        Npswd=new_pswd.getText().toString();
        Nfull_name=full_name.getText().toString().trim();
        Nphone=phone.getText().toString().trim();
        Ncountry=country.getText().toString().trim();
        Ndate_birth=date_birth.getText().toString().trim();
        User user  = new User(Nmail,Md5(Npswd),Ncountry,Nphone,Nfull_name,Ndate_birth);
        System.out.println(Nmail+Npswd);
        databaseReference.push().setValue(user);



    }

    public static String Md5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }


}


