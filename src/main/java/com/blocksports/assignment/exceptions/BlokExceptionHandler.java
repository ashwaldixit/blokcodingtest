package com.blocksports.assignment.exceptions;

import com.blocksports.assignment.enums.NotificationInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BlokExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpHeaders getHeaderWithContentType(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
    @ExceptionHandler(value = BlokBadRequestException.class)
    protected ResponseEntity<Object> handleException(BlokBadRequestException ex, WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler(value = BlokNotFoundException.class)
    protected ResponseEntity<Object> handleException(BlokNotFoundException ex, WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler(value = BlokServerException.class)
    protected ResponseEntity<Object> handleException(BlokServerException ex, WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler(value = ForbiddenException.class)
    protected ResponseEntity<Object> handleException(ForbiddenException ex, WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler(value = UnAuthenticatedException.class)
    protected ResponseEntity<Object> handleException(UnAuthenticatedException ex, WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }


    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionMessage exceptionMessage = new ExceptionMessage("Oh no!! Something went wrong. Please try again", NotificationInfo.ERROR,500,ex.getMessage());
//        return handleExceptionInternal(ex,exceptionMessage,
//                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

        return new ResponseEntity(exceptionMessage, getHeaderWithContentType(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    private ResponseEntity<Object> getObjectResponseEntity(BlokRuntimeException ex, WebRequest request) {

        return new ResponseEntity(ex.getExceptionMessage(), getHeaderWithContentType(),ex.getExceptionMessage().getCode());
    }
}

