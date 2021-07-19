package br.com.zupacademy.rafael.proposta.analiseproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analisedados", url = "${url.servico.analise}")
public interface PropostaAnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    String verificaStatus();

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    SolicitacaoResponse solicita(AnalisePropostaRequest request);
}
