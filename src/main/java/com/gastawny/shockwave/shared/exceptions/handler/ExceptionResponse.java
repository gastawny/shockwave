package com.gastawny.shockwave.shared.exceptions.handler;

import java.io.Serial;
import java.io.Serializable;

public record ExceptionResponse(String status, String message, String details) implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
}