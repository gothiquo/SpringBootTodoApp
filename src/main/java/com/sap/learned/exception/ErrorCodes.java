package com.sap.learned.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    USER_NOT_FOUND(1000),
    USER_NOT_VALID(1001),

    ROLE_NOT_FOUND(2000),
    ROLE_NOT_VALID(2001),

    TODO_NOT_FOUND(3000),
    TODO_NOT_VALID(3001);

    private int code;
}
