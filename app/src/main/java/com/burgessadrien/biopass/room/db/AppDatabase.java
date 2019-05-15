package com.burgessadrien.biopass.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.burgessadrien.biopass.room.entities.*;
import com.burgessadrien.biopass.room.dao.*;

@Database(
    entities = {
        Entry.class,
        Group.class,
    },
    version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "app_db";

    public abstract EntryDao getEntryDao();

    public abstract GroupDao getGroupDao();
}
