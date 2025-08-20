package com.example.simplecrm.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.simplecrm.dto.ErrorResponse;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.ObjectError;

@ControllerAdvice
public class GlobalExceptionHandler {

    // This is a handler for CustomerNotFoundException
    // @ExceptionHandler(CustomerNotFoundException.class)
    // public ResponseEntity<ErrorResponse>
    // handleCustomerNotFoundException(CustomerNotFoundException ex) {
    // return new ResponseEntity<>(
    // new ErrorResponse(ex.getMessage(), LocalDateTime.now()),
    // HttpStatus.NOT_FOUND);
    // }

    // // HANDLE InteractionNotFoundException
    // @ExceptionHandler(InteractionNotFoundException.class)
    // public ResponseEntity<ErrorResponse>
    // handleInteractionNotFoundException(InteractionNotFoundException ex) {
    // return new ResponseEntity<>(
    // new ErrorResponse(ex.getMessage(), LocalDateTime.now()),
    // HttpStatus.NOT_FOUND);
    // }

    // Handle both exceptions
    @ExceptionHandler({ CustomerNotFoundException.class, InteractionNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Validation Exception Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        // Get a list of all validation errors from the exception object
        List<ObjectError> validationErrors = e.getBindingResult().getAllErrors();

        // Create a StringBuilder to store all error messages
        StringBuilder sb = new StringBuilder();

        // Loop through all the errors and append error messages to the StringBuilder
        for (ObjectError error : validationErrors) {
            sb.append(error.getDefaultMessage() + ". ");
        }

        ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Log the actual exception message

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("😒Something went wrong", LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}