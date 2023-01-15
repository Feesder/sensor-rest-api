package com.project.sensor.exception;

import javax.security.sasl.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String detail) {
        super(detail);
    }

    public AuthException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
