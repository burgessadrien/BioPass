package com.burgessadrien.biopass.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.burgessadrien.biopass.realm.dao.GroupDao;
import com.burgessadrien.biopass.realm.objects.Group;

import java.util.List;

import io.realm.Realm;

public class GroupListViewModel extends ViewModel {

    private final Realm realm;
    private final GroupDao groupDao;
    private final LiveData<List<Group>> groups;

    public GroupListViewModel() {
        realm = Realm.getDefaultInstance();
        groupDao = new GroupDao(realm);
        groups = groupDao.loadAll();
    }

    public LiveData<List<Group>> getGroups() {
        return groups;
    }

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }
}
