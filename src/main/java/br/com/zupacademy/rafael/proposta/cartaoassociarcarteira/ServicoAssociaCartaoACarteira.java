package br.com.zupacademy.rafael.proposta.cartaoassociarcarteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(value = "api-associa-cartao-em-carteira", url = "http://localhost:8888")
public interface ServicoAssociaCartaoACarteira {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/carteiras", consumes = "application/json", produces = "application/json")
    public String associar(@PathVariable(value = "id") String id, @RequestBody @Valid AssociarCarteiraForm form);
}
