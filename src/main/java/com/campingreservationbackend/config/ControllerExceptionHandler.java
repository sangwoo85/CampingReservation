package com.campingreservationbackend.config;


import com.campingreservationbackend.constant.API_RESPONSE;
import com.campingreservationbackend.vo.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @title RestController Exception Handler Customer Configuration
 * */
@RestControllerAdvice
public class ControllerExceptionHandler {

    Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception ex){

        LOGGER.info("handlerException ======== START");
        LOGGER.info("handlerException ========"+ex.getMessage());
        ErrorResponse response = new ErrorResponse(API_RESPONSE.ERROR_DEFAULT);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
