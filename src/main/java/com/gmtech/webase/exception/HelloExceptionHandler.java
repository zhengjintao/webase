package com.gmtech.webase.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class HelloExceptionHandler extends ResponseEntityExceptionHandler {
    // Controller から throw される MyException を捕捉
    @ExceptionHandler(MyException.class)
    public ResponseEntity<Object> handleMyException(MyException exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        
        ErrorResponseBody response = createErrorResponseBody(exception, request);
        response.setErrorDetail(exception.getErrorDetails());
        return super.handleExceptionInternal(exception,
        		response,
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }
    
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handle500(Exception ex, WebRequest request) {
    	HttpHeaders headers = new HttpHeaders();
    	ErrorResponseBody response = createErrorResponseBody(ex, request);
        return super.handleExceptionInternal(ex,
        		response,
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }


    // レスポンスのボディ部を作成
    private ErrorResponseBody createErrorResponseBody(Exception exception, WebRequest request) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        int responseCode = HttpStatus.BAD_REQUEST.value();
        String responseErrorMessage = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();

        errorResponseBody.setExceptionOccurrenceTime(ZonedDateTime.now());
        errorResponseBody.setStatus(responseCode);
        errorResponseBody.setError(responseErrorMessage);
        errorResponseBody.setMessage(exception.getMessage());
        errorResponseBody.setPath(uri);

        return errorResponseBody;
    }

}