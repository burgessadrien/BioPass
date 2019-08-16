package com.burgessadrien.biopass.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.burgessadrien.biopass.realm.dao.EntryDao;
import com.burgessadrien.biopass.realm.objects.Entry;

import java.util.List;

import io.realm.Realm;

public class EntryListViewModel extends ViewModel {

    private final Realm realm;
    private final EntryDao entryDao;
    private final LiveData<List<Entry>> entries;

    public EntryListViewModel() {
        realm = Realm.getDefaultInstance();
        entryDao = new EntryDao(realm);
        entries = entryDao.loadAll();
    }

    public LiveData<List<Entry>> getEntries() {
        return entries;
    }

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }
}
