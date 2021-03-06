package com.example.a3634_assigment.Databases;

import android.content.Context;

import androidx.room.Room;

import com.example.a3634_assigment.Models.User;

public class SessionInfo {
    public static User currentUser = new User("username", "password", 1);
    public static UserDb mUserDatabase;
    public static NotesDb mNotesDatabase;

    //creates database for user
    public static void createDB(Context context) {

        mUserDatabase = Room.databaseBuilder(context.getApplicationContext(),
                UserDb.class, "user_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        mNotesDatabase = Room.databaseBuilder(context.getApplicationContext(),
                NotesDb.class, "notes_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    ;
}


