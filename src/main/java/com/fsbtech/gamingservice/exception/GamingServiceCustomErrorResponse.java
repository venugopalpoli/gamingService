package com.fsbtech.gamingservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamingServiceCustomErrorResponse {

    private String errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
    private Integer status;
}
