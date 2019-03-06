package com.user.realm.controller;

import com.user.realm.exceptions.RealmException;
import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user/realm")
public class RealmController {

    @Autowired
    RealmService realmService;

    @RequestMapping("/")
    public String home() {
        return "Spring boot is working!";
    }

    @GetMapping
    public List<Realm> list() {
        return realmService.getAllRealms();
    }

    @PostMapping
    public ResponseEntity<?> create(HttpEntity<String> httpEntity) {

        HttpHeaders n = httpEntity.getHeaders();
        System.out.println("HEADERS: " + n.toString());

        Object optional = realmService.saveOrUpdateRealm(httpEntity.getBody()).get();
        if (optional instanceof RealmError) {
            return new ResponseEntity<>(optional, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(optional, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {

        Object optional = realmService.getRealmRealmById(id).get();
        if (optional instanceof RealmError) {
            return new ResponseEntity<>(optional, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(optional, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        realmService.deleteRealm(id);
    }


}
