package br.com.zupacademy.rafael.proposta.novabiometria;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;
import br.com.zupacademy.rafael.proposta.validation.ValidadorBase64;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    @ValidadorBase64
    private String fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaBiometriaRequest(@NotBlank String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @Override
    public String toString() {
        return "NovaBiometriaRequest [fingerprint=" + fingerprint + "]";
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerprint, cartao);
    }

}
