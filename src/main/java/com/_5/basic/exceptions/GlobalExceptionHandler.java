package com._5.basic.exceptions;

import com._5.basic.model.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // Catches RuntimeException
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({BadCredentialsException.class, AccountStatusException.class, AccessDeniedException.class, SignatureException.class, ExpiredJwtException.class})
    public ResponseEntity<ProblemDetail> handleSecurityException(Exception exception) {
        ProblemDetail errorDetail;

        // TODO: Send this stack trace to an observability tool
        exception.printStackTrace();

        switch (exception) {
            case BadCredentialsException e -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
                errorDetail.setProperty("description", "The username or password is incorrect");
            }
            case AccountStatusException e -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
                errorDetail.setProperty("description", "The account is locked");
            }
            case AccessDeniedException e -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
                errorDetail.setProperty("description", "You are not authorized to access this resource");
            }
            case SignatureException e -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
                errorDetail.setProperty("description", "The JWT signature is invalid");
            }
            case ExpiredJwtException e -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
                errorDetail.setProperty("description", "The JWT token has expired");
            }
            default -> {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
                errorDetail.setProperty("description", "Unknown internal server error.");
            }
        }

        return ResponseEntity.status(errorDetail.getStatus()).body(errorDetail);
    }
}
