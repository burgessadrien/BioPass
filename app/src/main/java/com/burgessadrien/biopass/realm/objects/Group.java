package com.burgessadrien.biopass.realm.objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Group extends RealmObject {

    @PrimaryKey
    @Required
    private long id;

    @Required
    private String name;

    private RealmList<Entry> entries;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

}
