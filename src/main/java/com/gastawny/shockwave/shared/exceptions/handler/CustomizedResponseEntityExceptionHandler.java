package com.gastawny.shockwave.shared.exceptions.handler;

import com.gastawny.shockwave.shared.constants.CommonConstants;
import com.gastawny.shockwave.shared.exceptions.BadRequestException;
import com.gastawny.shockwave.shared.exceptions.InternalErrorException;
import com.gastawny.shockwave.shared.exceptions.ShockwaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(ShockwaveException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(ShockwaveException ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                CommonConstants.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> badRequestException(ShockwaveException ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getResponse().getStatus(), ex.getMessage(), req.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalErrorException.class)
    public final ResponseEntity<ExceptionResponse> internalErrorException(ShockwaveException ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getResponse().getStatus(), ex.getMessage(), req.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
