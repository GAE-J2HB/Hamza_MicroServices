package com.j2hb.cException;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
