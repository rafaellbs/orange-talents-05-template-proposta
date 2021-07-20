package br.com.zupacademy.rafael.proposta.analiseproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analisedados", url = "${url.servico.analise}")
public interface ServicoAnaliseProposta {

    @RequestMapping(method = RequestMethod.POST)
    String verificaStatus();

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    ResultadoAnalisePropostaDTO realiza(AnalisePropostaDTO request);
}
