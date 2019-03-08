package com.user.realm.model;

public class RealmError {

    public enum Code {
        DUPLICATE_NAME("DuplicateRealmName"),
        INVALID_REALM_NAME("InvalidRealmName"),
        KEY_PROVIDED("KeyProvided"),
        DESCRIPTION_TOO_LONG("DescriptionTooLong"),
        INVALID_ARGUMENT("InvalidArgument"),
        REALM_NOT_FOUND("RealmNotFound");

        public String message;

        Code(String message) {
            this.message = message;
        }
    }

    private Code code;

    public RealmError(Code code) {
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RealmError{" + "code=" + code + '}';
    }
}
