package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MySqlSessionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDetails> handleServiceException(Throwable e) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(e.getMessage(), HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
