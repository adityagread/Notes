package com.example.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NoteRepository {

    private NoteDoa mNoteDao;
    private LiveData<List<Note>> mALLNotes;
    NoteRepository(Application application){
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDoa();
        mALLNotes = mNoteDao.getAllNotes();
    }

    LiveData<List<Note>> getALLNotes(){
        return mALLNotes;
    }
    public void insert(Note note){
        new insertAsyncTask(mNoteDao).execute(note);
    }
    public void deleteAll(){ new deleteAllNotesAsyncTask(mNoteDao).execute();}
    public void deleteNote(Note note){new deleteNoteAsyncTask(mNoteDao).execute(note);}

    private static class insertAsyncTask extends AsyncTask<Note, Void,Void > {

        private NoteDoa mAsyncTaskDao;
        public insertAsyncTask(NoteDoa mNoteDao) {
            mAsyncTaskDao = mNoteDao;
        }

        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDoa mAsyncTaskDao;

        deleteAllNotesAsyncTask(NoteDoa dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDoa mAsyncTaskDao;

        deleteNoteAsyncTask(NoteDoa dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
