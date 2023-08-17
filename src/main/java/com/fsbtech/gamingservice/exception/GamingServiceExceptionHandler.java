package com.fsbtech.gamingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GamingServiceExceptionHandler {

    @ExceptionHandler(value = GamingServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GamingServiceCustomErrorResponse gamingServiceNotFoundException(GamingServiceNotFoundException gamingServiceException){
        GamingServiceCustomErrorResponse gamingServiceCustomErrorResponse = new GamingServiceCustomErrorResponse();
        gamingServiceCustomErrorResponse.setErrorCode("NOT_FOUND_ERROR");
        gamingServiceCustomErrorResponse.setErrorMessage(gamingServiceException.getMessage());
        gamingServiceCustomErrorResponse.setTimestamp(LocalDateTime.now());
        gamingServiceCustomErrorResponse.setStatus((HttpStatus.NOT_FOUND.value()));
        return gamingServiceCustomErrorResponse;
    }


}
