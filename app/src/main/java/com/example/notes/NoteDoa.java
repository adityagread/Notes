package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDoa {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Query("DELETE from notes_table")
    void deleteAll();

    @Query("select * from notes_table ORDER BY note ASC")
    LiveData<List<Note>> getAllNotes();

    @Query("select * from notes_table LIMIT 1")
    Note[] getAnyNote();

    @Delete
    void delete(Note param);
}
