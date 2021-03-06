package com.user.realm.controller;

import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.service.RealmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/realm")
class RealmController {

    @Autowired
    RealmService realmService;

    @GetMapping
    List<Realm> listRealms() {
        return realmService.getAllRealms();
    }

    @PostMapping
    ResponseEntity create(HttpEntity<String> httpEntity) {

        Object optional = realmService.saveOrUpdateRealm(httpEntity.getBody()).get();
        if (optional instanceof RealmError) {
            return new ResponseEntity<>(optional, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(optional, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity get(@PathVariable("id") Integer id) {

        Object optional = realmService.getRealmRealmById(id).get();
        if (optional instanceof RealmError) {
            return new ResponseEntity<>(optional, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(optional, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {
        realmService.deleteRealm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
