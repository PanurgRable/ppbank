package com.bank.profile.handler;

import java.time.OffsetDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

/**
 * DTO representing error payload returned by {@link GlobalExceptionHandler}.
 */
@Value
@Builder
public class ApiError {

    OffsetDateTime timestamp;
    int status;
    String error;
    String message;
    String path;
    Map<String, String> validationErrors;
}
