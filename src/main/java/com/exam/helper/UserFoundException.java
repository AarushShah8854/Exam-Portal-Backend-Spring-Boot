package com.exam.helper;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("User with this username is already there in DB!! Try again with new username");
    }

    public UserFoundException(String msg) {
        super(msg);
    }
}
