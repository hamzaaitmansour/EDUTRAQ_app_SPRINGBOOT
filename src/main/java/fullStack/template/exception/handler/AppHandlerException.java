package fullStack.template.exception.handler;

import fullStack.template.exception.EntityAlreadyExistException;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.exception.ErrorShared;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppHandlerException {
     @ExceptionHandler(value = {EntityAlreadyExistException.class})
    public ResponseEntity<Object> entityAlreadyExistException(EntityAlreadyExistException ex)
    {
        ErrorShared errorShared = ErrorShared.builder()
                .code(409)
                .message("entity Already Exist")
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorShared, HttpStatus.CONFLICT);
    }

     @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex)
    {
        ErrorShared errorShared = ErrorShared.builder()
                .code(409)
                .message("entity Already Exist")
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorShared, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors =new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(errors,new HttpHeaders(),HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
