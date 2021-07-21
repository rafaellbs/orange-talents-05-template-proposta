package br.com.zupacademy.rafael.proposta.cadastroavisoviagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(value = "notifica-sistema-aviso", url = "${servico.cartoes}")
public interface ServicoNotificaSistemaAviso {

    @RequestMapping(method = RequestMethod.POST,consumes = "application/json",
            produces = "application/json", value = "/{id}/avisos")
    String notificar(@PathVariable(name = "id") String id, @RequestBody @Valid AvisoViagemForm form);
}
