package com.upgrad.quora.api.exception;

import com.upgrad.quora.api.model.ErrorResponse;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.SignOutRestrictedException;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import com.upgrad.quora.service.exception.AnswerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class QuoraExceptionHandler {


  @ExceptionHandler(SignUpRestrictedException.class)
  public ResponseEntity<ErrorResponse> signUpRestrictedException(
      SignUpRestrictedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.CONFLICT
    );
  }

  @ExceptionHandler(AuthenticationFailedException.class)
  public ResponseEntity<ErrorResponse> authenticationFailedException(
      AuthenticationFailedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.UNAUTHORIZED
    );
  }

  @ExceptionHandler(SignOutRestrictedException.class)
  public ResponseEntity<ErrorResponse> signOutRestrictedException(
      SignOutRestrictedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.UNAUTHORIZED
    );
  }

  @ExceptionHandler(AuthorizationFailedException.class)
  public ResponseEntity<ErrorResponse> authorizationFailedException(
      AuthorizationFailedException exception, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.FORBIDDEN
    );
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException exception,
      WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exception.getCode()).message(exception.getErrorMessage()),
        HttpStatus.NOT_FOUND
    );
  }


  @ExceptionHandler(InvalidQuestionException.class)
  public ResponseEntity<ErrorResponse> invalidQuestionException(InvalidQuestionException exe,
      WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exe.getCode()).message(exe.getErrorMessage()), HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(AnswerNotFoundException.class)
  public ResponseEntity<ErrorResponse> AnswerNotFoundException(AnswerNotFoundException exe,
      WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exe.getCode()).message(exe.getErrorMessage()), HttpStatus.NOT_FOUND
    );
  }

}
