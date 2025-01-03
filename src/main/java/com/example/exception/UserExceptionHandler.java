package com.example.exception;

import com.example.dto.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(
            UserNotFoundException userNotFoundException){
        UserErrorResponse errorResponse=new UserErrorResponse();
        errorResponse.setMessage(String.valueOf(userNotFoundException.getMessage()));
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new  ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
