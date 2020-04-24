package com.example.mvvmcodinginflow;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> returnAllNotes;


    public NoteRepository(Application application){
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDao = noteDataBase.noteDao();
        returnAllNotes = noteDao.returnAllNotes();
    }

    public void insert(Note note) {
        new InsertAsync(noteDao).execute(note);

    }

    public void update(Note note) {
        new UpdateAsync(noteDao).execute(note);

    }

    public void delete(Note note) {
        new DeleteAsync(noteDao).execute(note);

    }

    public void deleteAllNotes() {
        new AllNotesAsync(noteDao).execute();

    }
    // take care of this method man
    public LiveData<List<Note>> getReturnAllNotes() {
        return returnAllNotes;
    }

    private static class InsertAsync extends AsyncTask<Note,Void,Void>{
        private NoteDao  noteDao;
        private InsertAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateAsync extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public UpdateAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAsync extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }


    }

    private static class AllNotesAsync extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private AllNotesAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }


}
