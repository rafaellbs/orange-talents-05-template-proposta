package br.com.zupacademy.rafael.proposta.bloqueiocartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "api-bloqueio-cartao", url = "${servico.cartoes}")
public interface ServicoBloqueioCartao {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json", value = "/{id}/bloqueios")
    String notifica(@PathVariable(name = "id") String id, @RequestBody BloqueioCartaoDTO dados);
}

class BloqueioCartaoDTO {

    private String sistemaResponsavel;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BloqueioCartaoDTO(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

}
