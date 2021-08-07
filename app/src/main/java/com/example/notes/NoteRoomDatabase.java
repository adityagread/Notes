package com.example.notes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDoa noteDoa();

    private static NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDoa mDao;
        String[] notes = {};

        PopulateDbAsync(NoteRoomDatabase db) {
            mDao = db.noteDoa();
        }

        @Override
        protected Void doInBackground(final Void... params) {


            if (mDao.getAnyNote().length < 1) {
                for (int i = 0; i <= notes.length - 1; i++) {
                    Note note = new Note(notes[i]);
                    mDao.insert(note);
                }
            }
            return null;
        }
    }
}
