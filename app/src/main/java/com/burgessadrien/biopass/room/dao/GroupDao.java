package com.burgessadrien.biopass.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.burgessadrien.biopass.room.entities.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void insert(Group group);

    @Query("DELETE FROM groups")
    void  deleteAll();

    @Query("SELECT * from groups")
    List<Group> getAllGroups();
}
