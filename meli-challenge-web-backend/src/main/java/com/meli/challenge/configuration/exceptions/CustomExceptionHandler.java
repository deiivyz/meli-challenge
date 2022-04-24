package com.meli.challenge.configuration.exceptions;

import com.meli.challenge.configuration.exceptions.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(
        value = Exception.class)
    public ResponseEntity<ErrorDTO> processSupportedExceptions(Exception ex) {

        return new ResponseEntity<>(ErrorDTO.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(ex.getClass().getSimpleName())
            .message(ex.getMessage())
            .build(),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<Void> invalidRequestDataConversion(HttpMessageConversionException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoMutantException.class)
    public ResponseEntity<Void> invalidDataException(NoMutantException ex) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
