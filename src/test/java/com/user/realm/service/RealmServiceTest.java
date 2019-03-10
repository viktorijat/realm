package com.user.realm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.repository.RealmRepository;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RealmServiceTest {

    private static final String DESCRIPTION = "descriptions";
    private static final String NAME = "abc";
    private static final String KEY = "1234";
    private static final long ID = 1L;

    @Mock
    RealmRepository realmRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    RealmService realmService;

    @Test
    public void shouldGetAllRealms() {

        List<Realm> realms = Collections.singletonList(new Realm(NAME, DESCRIPTION));

        when(realmRepository.findAll()).thenReturn(realms);
        assertThat(realmService.getAllRealms(), is(realms));
    }

    @Test
    public void shouldGetRealmById() {

        Realm realm = new Realm(NAME, DESCRIPTION);
        Optional optional = Optional.of(realm);

        when(realmRepository.findById(any())).thenReturn(optional);
        assertThat(realmService.getRealmRealmById(1), is(optional));
    }

    @Test
    public void shouldReturnInvalidArgumentOnSave() throws IOException {

        when(objectMapper.readValue(anyString(), eq(Realm.class))).thenThrow(new IOException());
        Optional returnOptional = Optional.of(new RealmError(RealmError.Code.INVALID_ARGUMENT));
        RealmError error = (RealmError) returnOptional.get();
        RealmError.Code code = error.getCode();

        RealmError received = (RealmError) realmService.saveOrUpdateRealm("").get();
        RealmError.Code receivedCode = received.getCode();

        assertThat(receivedCode, is(code));
    }

    @Test
    public void shouldReturnInvalidRealmNAMEOnSave() throws IOException {

        Realm realm = new Realm(ID, "", DESCRIPTION, KEY);
        Optional returnOptional = Optional.of(new RealmError(RealmError.Code.INVALID_REALM_NAME));
        RealmError error = (RealmError) returnOptional.get();
        RealmError.Code code = error.getCode();

        when((objectMapper).readValue(anyString(), eq(Realm.class))).thenReturn(realm);
        RealmError received = (RealmError) realmService.saveOrUpdateRealm(realm.toString()).get();
        RealmError.Code receivedCode = received.getCode();
        assertThat(receivedCode, is(code));
    }

    @Test
    public void shouldReturnKeyProvidedOnSave() throws IOException {

        Realm realm = new Realm(ID, NAME, DESCRIPTION, KEY);
        Optional returnOptional = Optional.of(new RealmError(RealmError.Code.KEY_PROVIDED));
        RealmError error = (RealmError) returnOptional.get();
        RealmError.Code code = error.getCode();

        when((objectMapper).readValue(anyString(), eq(Realm.class))).thenReturn(realm);
        RealmError received = (RealmError) realmService.saveOrUpdateRealm(realm.toString()).get();
        RealmError.Code receivedCode = received.getCode();
        assertThat(receivedCode, is(code));
    }

    @Test
    public void shouldReturnDescriptionTooLongOnSave() throws IOException {

        String longString = String.join("", Collections.nCopies(256, "*"));
        Realm realm = new Realm(ID, NAME, longString, "");
        Optional returnOptional = Optional.of(new RealmError(RealmError.Code.DESCRIPTION_TOO_LONG));
        RealmError error = (RealmError) returnOptional.get();
        RealmError.Code code = error.getCode();

        when((objectMapper).readValue(anyString(), eq(Realm.class))).thenReturn(realm);
        RealmError received = (RealmError) realmService.saveOrUpdateRealm(realm.toString()).get();
        RealmError.Code receivedCode = received.getCode();
        assertThat(receivedCode, is(code));
    }

    @Test
    public void shouldReturnDuplicateNAMEOnSave() throws IOException {

        Realm realm = new Realm(ID, NAME, DESCRIPTION, "");
        Optional returnOptional = Optional.of(new RealmError(RealmError.Code.DUPLICATE_NAME));
        RealmError error = (RealmError) returnOptional.get();
        RealmError.Code code = error.getCode();

        when(realmRepository.findByName(realm.getName())).thenReturn(realm);
        when((objectMapper).readValue(anyString(), eq(Realm.class))).thenReturn(realm);

        RealmError received = (RealmError) realmService.saveOrUpdateRealm(realm.toString()).get();
        RealmError.Code receivedCode = received.getCode();
        assertThat(receivedCode, is(code));
    }

    @Test
    public void shouldSaveRealmSuccessfully() throws IOException {

        Realm realm = new Realm(ID, NAME, DESCRIPTION, "");
        when((objectMapper).readValue(anyString(), eq(Realm.class))).thenReturn(realm);
        when(realmRepository.save(any())).thenReturn(realm);

        Realm received = (Realm) realmService.saveOrUpdateRealm(realm.toString()).get();
        assertThat(realm, is(received));
    }
}