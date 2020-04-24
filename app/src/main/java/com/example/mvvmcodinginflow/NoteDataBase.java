package com.example.mvvmcodinginflow;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase instance;
    public  abstract NoteDao noteDao();
    public static synchronized  NoteDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class
            ,"NoteDb").fallbackToDestructiveMigration().addCallback(room).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback room = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new CreateAsync(instance).execute();
        }
    };
    private static class CreateAsync extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private CreateAsync(NoteDataBase database){
            noteDao = database.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title1","description 1",1));
            noteDao.insert(new Note("title2","description 2",2));
            noteDao.insert(new Note("title3","description 3",3));
            return null;
        }
    }
}
