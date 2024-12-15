package com.example.GoShare.exception;

import javax.naming.AuthenticationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.GoShare.dto.response.ErrorResponse;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catch DataIntegrityViolationException when have duplicate value in database.
     * 
     * @param exception DataIntegrityViolationException
     * @return Respone error
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(
            DataIntegrityViolationException exception) {

        return ErrorResponse
                .builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .build();
    }

    /**
     * Catch AuthenticationException when user name or password incorrect
     * 
     * @param exception
     * @return Respone error
     */
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleAuthenticationException(AuthenticationException exception) {

        return ErrorResponse
                .builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .build();
    }

    /**
     * Catch AccessDeniedException when user dont have permission
     * 
     * @param exception
     * @return Respone error
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
        return ErrorResponse
                .builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(exception.getMessage())
                .build();
    }

    /**
     * Catch SignatureException when JWT signature does not match locally computed
     * signature. JWT validity cannot be asserted and should not be trusted
     * 
     * @param exception
     * @return Respone error
     */
    @ExceptionHandler(JwtException.class)
    public ErrorResponse handleJwtException(JwtException exception) {
        return ErrorResponse
                .builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse hanndleInternalServerError(Exception exception) {
        exception.printStackTrace();
        return ErrorResponse
                .builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .build();
    }

}