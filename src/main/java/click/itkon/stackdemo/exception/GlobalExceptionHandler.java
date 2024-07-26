package click.itkon.stackdemo.exception;

import click.itkon.stackdemo.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StackEmptyException.class)
    public ResponseEntity<ErrorResponseDto> handleStackEmptyException(StackEmptyException e, WebRequest webRequest) {
        return getResponse(HttpStatus.NOT_FOUND, webRequest, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {
        HashMap<String, String> validationErrors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(objectError ->
                validationErrors.put(
                        ((FieldError) objectError).getField(),
                        objectError.getDefaultMessage()));

        return getResponse(HttpStatus.BAD_REQUEST, webRequest, validationErrors.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalExceptions(Exception e, WebRequest webRequest) {
        return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, webRequest, e.getMessage());
    }


    private ResponseEntity<ErrorResponseDto> getResponse(HttpStatus httpStatus, WebRequest webRequest, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(ErrorResponseDto.builder()
                        .apiPath(webRequest.getDescription(false).replaceFirst("uri=", ""))
                        .errorCode(httpStatus)
                        .errorMessage(message)
                        .errorTime(LocalDateTime.now())
                        .build());
    }
}
