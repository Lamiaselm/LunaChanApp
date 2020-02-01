package com.example.lunachan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyNoteDetail extends AppCompatActivity {
    private EditText date_note;
    private EditText time_note;
    private EditText myNote;
    private String Sdate_note;
    private String Stime_note;
    private String  SmyNote;
    private Button valider;
    private  Button delete;
    private  Button retour;
    public  static String noteID;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note_detail);
        date_note=(EditText)findViewById(R.id.note_date);
        time_note=(EditText)findViewById(R.id.note_time);
        myNote=(EditText)findViewById(R.id.myNote);
        valider=(Button)findViewById(R.id.valider);
        delete=(Button)findViewById(R.id.delete_note2);
        retour=(Button)findViewById(R.id.retour);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sdate_note=date_note.getText().toString();
                Stime_note=time_note.getText().toString();
                SmyNote=myNote.getText().toString();
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference().child("Users").child(LoginActivity.UID).child("MyNote");
                MyNote mynote=new MyNote(Sdate_note,Stime_note,SmyNote);
                noteID=databaseReference.push().getKey();
                databaseReference.child(noteID).setValue(mynote);

                Toast.makeText(MyNoteDetail.this, "Your note is saved YAY ! *-* ", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(MyNoteDetail.this,MyNotesActivity.class);
                startActivity(intent2);


            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MyNoteDetail.this,MyNotesActivity.class);
                startActivity(intent);
            }
        });

    }
}
