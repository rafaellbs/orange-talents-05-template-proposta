package br.com.zupacademy.rafael.proposta.cadastroavisoviagem;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemForm {

    @ NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate dataTermino;

    public AvisoViagemForm(@NotBlank String destino, @Future LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    @Override
    public String toString() {
        return "AvisoViagemForm [destino=" + destino + ", dataTermino=" + dataTermino + "]";
    }

    public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
        return new AvisoViagem(destino, dataTermino, ipCliente, userAgent, cartao);
    }
}
