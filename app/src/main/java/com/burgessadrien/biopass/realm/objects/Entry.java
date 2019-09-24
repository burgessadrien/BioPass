package com.burgessadrien.biopass.realm.objects;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Entry extends RealmObject {

    @PrimaryKey
    private long id;
    @Required
    private String site;

    @Required
    private String password;

    @Required
    private String username;

    private Note note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
