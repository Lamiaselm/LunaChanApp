package com.example.lunachan;

import android.widget.EditText;

public class MyNote {
    private String date_note;
    private String time_note;
    private String myNote;

    public MyNote() {
    }

    public MyNote(String date_note, String time_note, String myNote) {
        this.date_note = date_note;
        this.time_note = time_note;
        this.myNote = myNote;
    }

    public String getDate_note() {
        return date_note;
    }

    public void setDate_note(String date_note) {
        this.date_note = date_note;
    }

    public String getTime_note() {
        return time_note;
    }

    public void setTime_note(String time_note) {
        this.time_note = time_note;
    }

    public String getMyNote() {
        return myNote;
    }

    public void setMyNote(String myNote) {
        this.myNote = myNote;
    }
}
