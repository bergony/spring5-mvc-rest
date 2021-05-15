package springframeworkguru.spring5mvcrest.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springframeworkguru.spring5mvcrest.services.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExeceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handlerNotFoundExcetion(Exception  exception, WebRequest request){

        return new ResponseEntity<>("Resource Not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
