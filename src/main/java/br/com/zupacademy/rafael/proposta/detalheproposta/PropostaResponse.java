package br.com.zupacademy.rafael.proposta.detalheproposta;

import br.com.zupacademy.rafael.proposta.novaproposta.Proposta;
import br.com.zupacademy.rafael.proposta.novaproposta.Status;

public class PropostaResponse {
    private String nome;
    private Status status;

    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.status = proposta.getStatus();
    }

    public String getNome() {
        return nome;
    }

    public Status getStatus() {
        return status;
    }
}
