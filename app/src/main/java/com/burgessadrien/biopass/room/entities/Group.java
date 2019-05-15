package com.burgessadrien.biopass.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Date;


@Entity(tableName = "groups")
public class Group {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "last_update")
    private Date mDate;

    @Ignore
    public Group(String name) {
        mName = name;
        mDate = new Date(System.currentTimeMillis());
    }

    public Group(int id, String name, Date date) {
        this.mId = id;
        this.mName = name;
        this.mDate = date;
    }
}
