package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;
    public NoteViewModel(Application application) {
        super(application);
        mRepository = new NoteRepository((application));
        mAllNotes = mRepository.getALLNotes();
    }

    LiveData<List<Note>> getAllNotes(){return mAllNotes;}
    public void insert(Note note){
        mRepository.insert(note);
    }
    public void deleteAll(){ mRepository.deleteAll();}
    public void deleteNote(Note note){mRepository.deleteNote(note);}
}
