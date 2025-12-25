package com.gastawny.shockwave.shared.exceptions;

import com.gastawny.shockwave.shared.ShockwaveResponse;
import com.gastawny.shockwave.shared.constants.CommonConstants;

import java.io.Serial;

public class BadRequestException extends ShockwaveException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(final String message) {
        super(new ShockwaveResponse<>(CommonConstants.BAD_REQUEST, message));
    }
}
