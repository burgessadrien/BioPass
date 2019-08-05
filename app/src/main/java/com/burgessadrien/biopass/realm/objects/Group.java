package com.burgessadrien.biopass.realm.objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Group extends RealmObject {

    @PrimaryKey
    private String id;

    @Required
    private String name;

    private RealmList<Entry> entries;

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

}
