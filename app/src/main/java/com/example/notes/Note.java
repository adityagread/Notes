package com.example.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    @ColumnInfo(name = "note")
    public String mnote;

    public Note(@NonNull String note){this.mnote = note;}
    public Note(){}
    public String getMnote(){return this.mnote;}
}
