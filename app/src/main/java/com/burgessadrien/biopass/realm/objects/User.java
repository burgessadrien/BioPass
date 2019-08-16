package com.burgessadrien.biopass.realm.objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {

    @PrimaryKey
    @Required
    private Long id;

    private String name;

    private RealmList<Entry> entries;
    private RealmList<Group> groups;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
}
