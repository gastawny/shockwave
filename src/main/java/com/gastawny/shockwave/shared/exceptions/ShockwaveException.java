package com.gastawny.shockwave.shared.exceptions;

import com.gastawny.shockwave.shared.ShockwaveResponse;

import java.io.Serial;

public class ShockwaveException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ShockwaveResponse<?> response;

    public ShockwaveException(ShockwaveResponse<?> response) {
        super(response.getMessage());
        this.response = response;
    }

    public ShockwaveResponse<?> getResponse() {
        return response;
    }
}
