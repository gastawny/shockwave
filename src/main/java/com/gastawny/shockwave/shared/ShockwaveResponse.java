package com.gastawny.shockwave.shared;

import com.gastawny.shockwave.shared.constants.CommonConstants;

import java.io.Serial;
import java.io.Serializable;

public class ShockwaveResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String status;

    private final String message;

    private final T data;

    public ShockwaveResponse(T data) {
        this.status = CommonConstants.OK;
        this.message = CommonConstants.SUCCESS_MESSAGE;
        this.data = data;
    }

    public ShockwaveResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ShockwaveResponse(String status, String message) {
        this(status, message, null);
    }


    public String getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
