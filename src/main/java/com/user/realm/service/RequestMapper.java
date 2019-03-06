package com.user.realm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.user.realm.model.Realm;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class RequestMapper {

    public Realm unmarshal(String type, String text) throws JAXBException, IOException {

        if (type.contains("xml")) {
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(text, Realm.class);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(text, Realm.class);
        }
    }
}
