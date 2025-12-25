package com.gastawny.shockwave.shared.constants;

import org.springframework.http.HttpStatus;

public class CommonConstants {

    public static final String OK = HttpStatus.OK.name();
    public static final String CREATED = HttpStatus.CREATED.name();
    public static final String BAD_REQUEST = HttpStatus.BAD_REQUEST.name();
    public static final String INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.name();
    public static final String NOT_FOUND = HttpStatus.NOT_FOUND.name();
    public static final String FORBIDDEN = HttpStatus.FORBIDDEN.name();
    public static final String SUCCESS_MESSAGE = "Success";
    public static final String UTF_8 = "UTF-8";

    private CommonConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
