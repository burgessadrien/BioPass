package com.burgessadrien.biopass.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(
    tableName = "entries",
    foreignKeys = @ForeignKey(
            entity = Group.class,
            parentColumns = "id",
            childColumns = "group_id",
            onDelete = ForeignKey.CASCADE
    )
)
public class Entry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "group_id")
    private int mGroupId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "last_update")
    private Date mDate;

    @Ignore
    public Entry(String name) {
        mName = name;
        mDate = new Date(System.currentTimeMillis());
    }

    public Entry(int id, String name, Date date) {
        this.mId = id;
        this.mName = name;
        this.mDate = date;
    }
}
