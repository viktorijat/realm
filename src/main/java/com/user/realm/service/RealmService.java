package com.user.realm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RealmService {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RealmRepository realmRepository;

    public List<Realm> getAllRealms() {
        List<Realm> realm = new ArrayList<>();
        realmRepository.findAll().forEach(realm::add);
        return realm;
    }

    public Optional<?> getRealmRealmById(Integer id) {

        Optional<Realm> found = realmRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return Optional.of(found);
        }
        return Optional.of(new RealmError(RealmError.Code.REALM_NOT_FOUND));
    }

    public Optional<?> saveOrUpdateRealm(String input) {

        Realm realm;
        try {
            realm = objectMapper.readValue(input, Realm.class);
        } catch (IOException e) {
            return Optional.of(new RealmError(RealmError.Code.INVALID_ARGUMENT));
        }

        if (realm.getName() == null) {
            return Optional.of(new RealmError(RealmError.Code.INVALID_REALM_NAME));
        }

        if (realm.getKey() != null) {
            return Optional.of(new RealmError(RealmError.Code.KEY_PROVIDED));
        }

        if (realm.getDescription() != null && realm.getDescription().length() > 255) {
            return Optional.of(new RealmError(RealmError.Code.DESCRIPTION_TOO_LONG));
        }

        Realm found = realmRepository.findByName(realm.getName());
        if (found != null) {
            return Optional.of(new RealmError(RealmError.Code.DUPLICATE_NAME));
        }

        realm.setKey(KeyCreator.getKey());
        return Optional.of(realmRepository.save(realm));
    }

    public void deleteRealm(Long id) {
        realmRepository.deleteById(id);
    }

}
