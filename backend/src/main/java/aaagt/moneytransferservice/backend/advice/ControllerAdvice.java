package aaagt.moneytransferservice.backend.advice;

import aaagt.moneytransferservice.backend.dto.ErrorResponseDto;
import aaagt.moneytransferservice.backend.exception.ErrorInputData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<ErrorResponseDto> hande(ErrorInputData exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(exception.getMessage(), exception.getId()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> hande(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
    
}
