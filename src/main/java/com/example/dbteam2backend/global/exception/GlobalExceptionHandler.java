package com.example.dbteam2backend.global.exception;


import com.example.dbteam2backend.global.api.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<?> handleCustomException(CustomException e){
        return ApiResponse.error(e.getErrorCode().message());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e){
        return ApiResponse.error("서버 오류가 발생했습니다: " + e.getMessage());
    }
}
