package com.example.lunachan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyNotesActivity extends AppCompatActivity {
    private Button add_note;
    private Button delete_note;
    private Integer cpt ;
    private List<MyNote> mynote;
    private TextView viewNote;
    private String SviewNote;
    private TextView date_note;
    private TextView time_note;
    private TextView myNote;
    private String Sdate_note;
    private String Stime_note;
    private String  SmyNote;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        firebaseDatabase=FirebaseDatabase.getInstance();
databaseReference=firebaseDatabase.getReference().child("Users").child(LoginActivity.UID).child("MyNote");
        time_note=(TextView)findViewById(R.id.viewtime);
        myNote=(TextView)findViewById(R.id.viewdetail);
        add_note=(Button)findViewById(R.id.add_note);
        viewNote=(TextView)findViewById(R.id.viewNote);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(MyNoteDetail.noteID);

                       SviewNote=dataSnapshot.child(MyNoteDetail.noteID).child("date_note").getValue().toString();
                       Stime_note=dataSnapshot.child(MyNoteDetail.noteID).child("time_note").getValue().toString();

                       SmyNote=dataSnapshot.child(MyNoteDetail.noteID).child("myNote").getValue().toString();

                       viewNote.setText(SviewNote);
                       time_note.setText(Stime_note);
                       myNote.setText(SmyNote);









            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyNotesActivity.this,MyNoteDetail.class);
                startActivity(intent);



            }
        });





    }
}
