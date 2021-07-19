package br.com.zupacademy.rafael.proposta.erroreexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ControllerHandleAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> handleErrorValidation(MethodArgumentNotValidException exception) {
        Collection<String> mensagens = new ArrayList<String>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadronizado);
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<?> handleApiErrorException(ApiErrorException exception) {
        Collection<String> mensagens = new ArrayList<String>();

        mensagens.add(exception.getMotivo());

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);

        return ResponseEntity.status(exception.getStatus()).body(erroPadronizado);
    }
}
