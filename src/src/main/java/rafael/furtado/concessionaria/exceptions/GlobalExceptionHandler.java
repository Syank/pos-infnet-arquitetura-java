package rafael.furtado.concessionaria.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<String> handleException(InvalidAddressException e) {
        e.printStackTrace();

        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
