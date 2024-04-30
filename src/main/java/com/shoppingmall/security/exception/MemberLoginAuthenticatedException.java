package com.shoppingmall.security.exception;


import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class MemberLoginAuthenticatedException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MemberLoginAuthenticatedException(String message) {
        super(message);
    }

    public MemberLoginAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
