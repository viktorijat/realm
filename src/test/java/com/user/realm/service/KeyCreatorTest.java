package com.user.realm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.is;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class KeyCreatorTest {

    @Test
    public void shouldGenerateKey() {
        assertThat(KeyCreator.getKey().length(), is(32));
    }
}