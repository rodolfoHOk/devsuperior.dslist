package br.com.hiokdev.dslist.api.exceptionhandler;

import br.com.hiokdev.dslist.domain.exceptions.BusinessException;
import br.com.hiokdev.dslist.domain.exceptions.ResourceNotFoundException;
import br.com.hiokdev.dslist.domain.exceptions.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> businessExceptionHandler(BusinessException ex, WebRequest request) {
    HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;
    HttpHeaders headers = new HttpHeaders();
    ProblemDetail body = ProblemDetail.forStatus(statusCode);
    body.setDetail(ex.getMessage());

    return handleExceptionInternal(ex, body, headers, statusCode, request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
    HttpStatusCode statusCode = HttpStatus.NOT_FOUND;
    HttpHeaders headers = new HttpHeaders();
    ProblemDetail body = ProblemDetail.forStatus(statusCode);
    body.setDetail(ex.getMessage());

    return handleExceptionInternal(ex, body, headers, statusCode, request);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<?> validationExceptionHandler(ValidationException ex, WebRequest request) {
    HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;
    HttpHeaders headers = new HttpHeaders();
    ProblemDetail body = ProblemDetail.forStatus(statusCode);
    body.setDetail(ex.getMessage());

    return handleExceptionInternal(ex, body, headers, statusCode, request);
  }

}
