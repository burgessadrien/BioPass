package com.burgessadrien.biopass.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.burgessadrien.biopass.room.entities.Entry;

import java.util.List;

@Dao
public interface EntryDao {
    @Insert
    void insert(Entry entry);

    @Query("DELETE FROM entries")
    void  deleteAll();

    @Query("SELECT * from entries")
    List<Entry> getAllGroups();
}
