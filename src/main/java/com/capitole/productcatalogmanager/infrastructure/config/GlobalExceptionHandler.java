package com.capitole.productcatalogmanager.infrastructure.config;

import com.capitole.productcatalogmanager.infrastructure.rest.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for the application.
 * <p>
 * This class provides centralized exception handling across all controllers.
 * It catches specific exceptions and returns appropriate HTTP responses with error messages.
 * </p>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link PropertyReferenceException}, which occurs when an invalid property
     * reference is used in a query.
     *
     * @param ex      The exception that was thrown.
     * @param request The current web request.
     * @return A {@link ResponseEntity} containing an {@link ErrorDto} with a BAD_REQUEST status.
     */
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorDto> handlePropertyReferenceException(PropertyReferenceException ex, WebRequest request) {
        log.warn("Property reference error: {}", ex.getMessage());
        ErrorDto errorResponse = buildErrorResponse("Property reference error");
        return buildResponseEntity(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
    }

    /**
     * Handles {@link IllegalArgumentException}, which occurs when an invalid argument is passed.
     *
     * @param ex      The exception that was thrown.
     * @param request The current web request.
     * @return A {@link ResponseEntity} containing an {@link ErrorDto} with a BAD_REQUEST status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.warn("Invalid argument: {}", ex.getMessage());
        ErrorDto errorResponse = buildErrorResponse("Invalid argument");
        return buildResponseEntity(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
    }

    private ErrorDto buildErrorResponse(String message) {
        return new ErrorDto(message);
    }

    private ResponseEntity<ErrorDto> buildResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(buildErrorResponse(message));
    }
}
