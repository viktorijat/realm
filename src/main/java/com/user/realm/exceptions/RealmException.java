package com.user.realm.exceptions;

public class RealmException extends RuntimeException {

    public RealmException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealmException(String message) {
        super(message);
    }
}
