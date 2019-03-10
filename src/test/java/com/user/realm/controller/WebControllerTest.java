package com.user.realm.controller;

import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.service.RealmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WebControllerTest {

    @Mock
    BindingResult bindingResult;

    @Mock
    RealmService realmService;

    @InjectMocks
    WebController webController;

    @Test
    public void shouldReturnRealmUsersMapping() {
        List<Realm> realms = Collections.singletonList(new Realm("name", "description"));
        when(realmService.getAllRealms()).thenReturn(realms);
        assertThat(webController.realmUsers(new HashMap<>()), is("realmUsers"));
    }

    @Test
    public void shouldReturnNewRealmMapping() {
        assertThat(webController.newUser(new ModelMap()), is("newrealm"));
    }

    @Test
    public void shouldReloadPageIfErrors() {

        when((bindingResult).hasErrors()).thenReturn(true);
        assertThat(webController.saveRealm(new Realm(), bindingResult, null), is("newrealm"));
    }

    @Test
    public void shouldReloadPageSaveFails() {

        when((bindingResult).hasErrors()).thenReturn(false);
        Optional realmError = Optional.of(new RealmError(RealmError.Code.DESCRIPTION_TOO_LONG));
        when((realmService).saveOrUpdateRealm(any(Realm.class))).thenReturn(realmError);
        assertThat(webController.saveRealm(new Realm(), bindingResult, null), is("newrealm"));
    }

    @Test
    public void shouldGoToSuccessPageIfSaveSucceeds() {

        when((bindingResult).hasErrors()).thenReturn(false);
        Optional realmOptional = Optional.of(Realm.class);
        when((realmService).saveOrUpdateRealm(any(Realm.class))).thenReturn(realmOptional);
        ModelMap modelMap = new ModelMap();
        assertThat(webController.saveRealm(new Realm(), bindingResult, modelMap), is("success"));
        assertThat(modelMap.get("success"), is("Realm null created successfully"));
    }
}