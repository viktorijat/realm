package com.user.realm.repository;

import com.user.realm.model.Realm;
import com.user.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    RealmService realmService;

    @Override
    public void run(ApplicationArguments args) {
        realmService.saveOrUpdateRealm(new Realm("abcd", "description 1"));
        realmService.saveOrUpdateRealm(new Realm("efg", "description 2"));
        realmService.saveOrUpdateRealm(new Realm("hijk", "description 3"));
        realmService.saveOrUpdateRealm(new Realm("lmn", "description 4"));
        realmService.saveOrUpdateRealm(new Realm("opq", "description 5"));
    }

    @Autowired
    public DataLoader(RealmService realmService) {
        this.realmService = realmService;
    }
}
