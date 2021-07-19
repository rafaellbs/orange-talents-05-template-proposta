package br.com.zupacademy.rafael.proposta.novaproposta;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaPropostaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request,
                                       UriComponentsBuilder uriBuilder) {
        Proposta novaProposta = request.toModel();

        manager.persist(novaProposta);

        URI enderecoProposta = uriBuilder.path("/propostas/{id}").build(novaProposta.getId());

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, enderecoProposta.toString()).build();
    }
}