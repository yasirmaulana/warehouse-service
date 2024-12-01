package io.github.yasirmaulana.warehouse_service.extention;

import io.github.yasirmaulana.warehouse_service.dto.WebResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<WebResponse<Void>> handleNotFoundException(NotFoundException ex) {
        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("ERROR")
                .code("404")
                .message("Data not found: " + ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<WebResponse<Void>> handleDataAccessException(DataAccessException ex) {
        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("Database Error")
                .code("500")
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<WebResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("Bad Request")
                .code("400")
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .reduce("", (acc, error) -> acc + error + "; ");

        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("Error")
                .code("400")
                .message("Validation failed: " + errors)
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<Void>> handleGeneralException(Exception ex) {
        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("ERROR")
                .code("500")
                .message("An error occured: " + ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
