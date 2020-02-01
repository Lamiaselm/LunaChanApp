package com.example.lunachan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileSama extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String Sname_view;
    private String Sphone_view;
    private String Scountry_view;
    private String Sbirth_view;
    private String Semail_view;
    private TextView name_view;
    private TextView phone_view;
    private TextView country_view;
    private TextView birth_view;
    private TextView email_view;
    private Button go_notes;
    private Button addNote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sama);
        name_view=(TextView)findViewById(R.id.name_view);
        country_view=(TextView)findViewById(R.id.country_view);
        phone_view=(TextView)findViewById(R.id.phone_view);
        birth_view=(TextView)findViewById(R.id.birth_view);
        email_view=(TextView)findViewById(R.id.email_view) ;
        go_notes=(Button)findViewById(R.id.go_notes);
        addNote=(Button)findViewById(R.id.addNote);
        //<---------------------------FireBase Connexion ---------------------------------------------->

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users").child(LoginActivity.UID);



            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Sname_view=dataSnapshot.child("full_name").getValue().toString();
                    Sphone_view=dataSnapshot.child("phone").getValue().toString();
                    Scountry_view=dataSnapshot.child("country").getValue().toString();
                    Semail_view=dataSnapshot.child("mail").getValue().toString();
                    Sbirth_view=dataSnapshot.child("date_birth").getValue().toString();
                    name_view.setText(Sname_view);
                    phone_view.setText(Sphone_view);
                    country_view.setText(Scountry_view);
                    birth_view.setText(Sbirth_view);
                    email_view.setText(Semail_view);



//                    for(DataSnapshot readData: dataSnapshot.getChildren()){
//                        if (readData.getKey().equals(LoginActivity.UID)){
//
//                         System.out.println("country is "+readData.child("country"));}
//
//                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ProfileSama.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
            go_notes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2=new Intent(ProfileSama.this,MyNotesActivity.class);
                    startActivity(intent2);
                }
            });
            addNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2=new Intent(ProfileSama.this,MyNoteDetail.class);
                    startActivity(intent2);
                }
            });

    }

}
