package com.burgessadrien.biopass.realm.objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {

    @PrimaryKey
    private int id;

    private RealmList<Entry> entries;
    private RealmList<Group> groups;
}
