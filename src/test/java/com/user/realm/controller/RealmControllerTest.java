package com.user.realm.controller;

import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.service.RealmService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RealmControllerTest {

    @Mock
    RealmService realmService;

    @InjectMocks
    RealmController realmController;

    @Test
    public void shouldReturnConflictIfRealmCannotBeCreated() {

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        Optional optional = Optional.of(new RealmError(RealmError.Code.INVALID_REALM_NAME));
        when(realmService.saveOrUpdateRealm(anyString())).thenReturn(optional);

        ResponseEntity responseEntity = realmController.create(httpEntity);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CONFLICT));
        assertThat(responseEntity.getBody(), is(optional.get()));
    }

    @Test
    public void shouldReturnSuccessIfRealmCanBeCreated() {

        Realm realm = new Realm("name", "description");
        HttpEntity<String> httpEntity = new HttpEntity<>(realm.toString());

        Optional optional = Optional.of(realm);
        when(realmService.saveOrUpdateRealm(anyString())).thenReturn(optional);

        ResponseEntity responseEntity = realmController.create(httpEntity);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody(), is(optional.get()));
    }

    @Test
    public void shouldReturnNotFoundIfRealmDoesNotExist() {

        Optional optional = Optional.of(new RealmError(RealmError.Code.REALM_NOT_FOUND));
        when(realmService.getRealmRealmById(any())).thenReturn(optional);

        ResponseEntity responseEntity = realmController.get(1);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(responseEntity.getBody(), is(optional.get()));
    }

    @Test
    public void shouldReturnRealmIfRealmDoesExist() {

        Realm realm = new Realm("name", "description");
        Optional optional = Optional.of(realm);
        when(realmService.getRealmRealmById(any())).thenReturn(optional);

        ResponseEntity responseEntity = realmController.get(1);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(optional.get()));
    }

    @Test
    public void shouldDeleteRealm() {

        ResponseEntity responseEntity = realmController.delete(1L);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    public void shouldGetListOfRealms() {

        List<Realm> realms = Collections.singletonList(new Realm("name", "description"));
        when(realmService.getAllRealms()).thenReturn(realms);

        assertThat(realmController.listRealms(), is(realms));

    }
}