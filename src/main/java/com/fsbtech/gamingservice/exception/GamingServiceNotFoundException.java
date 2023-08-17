package com.fsbtech.gamingservice.exception;

import org.springframework.stereotype.Component;

@Component
public class GamingServiceNotFoundException extends RuntimeException{

    public GamingServiceNotFoundException(){
        super();
    }

    public GamingServiceNotFoundException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "GamingServiceException: " + super.toString();
    }


}
