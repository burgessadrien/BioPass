package com.burgessadrien.biopass.realm.objects;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Entry extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String password;

    @Required
    private String app;

    private String username;

    private String site;

    private String note;

    @Required
    private Date lastUpdated;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
