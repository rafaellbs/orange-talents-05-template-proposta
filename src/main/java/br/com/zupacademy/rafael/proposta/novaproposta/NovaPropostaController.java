package br.com.zupacademy.rafael.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class NovaPropostaController {

    @Autowired
    private PropostaRepository repository;

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request,
                                       UriComponentsBuilder uriBuilder) {
        List<Proposta> possivelProposta = repository.findByDocumento(request.getDocumento());

        if (possivelProposta.size() > 0) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        Proposta novaProposta = request.toModel();

        repository.save(novaProposta);

        URI enderecoProposta = uriBuilder.path("/propostas/{id}").build(novaProposta.getId());

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, enderecoProposta.toString()).build();
    }
}