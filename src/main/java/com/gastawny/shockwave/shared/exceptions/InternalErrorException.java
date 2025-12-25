package com.gastawny.shockwave.shared.exceptions;

import com.gastawny.shockwave.shared.ShockwaveResponse;
import com.gastawny.shockwave.shared.constants.CommonConstants;

import java.io.Serial;

public class InternalErrorException extends ShockwaveException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalErrorException(final String message) {
        super(new ShockwaveResponse<>(CommonConstants.INTERNAL_SERVER_ERROR, message));
    }
}
