package pdp.uz.rentcar.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pdp.uz.rentcar.exception.RecordNotFoundException;

import java.util.List;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorResponseField> list = exception.getBindingResult().getFieldErrors().stream().map(error -> ErrorResponseField.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .build())
                .toList();

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .fields(list)
                .build();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorResponse recordNotFoundException(RecordNotFoundException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

}
