package br.com.zupacademy.rafael.proposta.erroreexception;

import org.springframework.http.HttpStatus;

public class ApiErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatus status;
    private final String motivo;

    public ApiErrorException(HttpStatus status, String motivo) {
        super(motivo);
        this.status = status;
        this.motivo = motivo;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMotivo() {
        return motivo;
    }
}
