package br.com.zupacademy.rafael.proposta.cartaoassociarcarteira;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;
import br.com.zupacademy.rafael.proposta.criarcartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class AssociarCarteiraController {

    @Autowired
    private ServicoAssociaCartaoACarteira servicoAssociaCartaoACarteira;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private EntityManager manager;

    @PostMapping("/cartoes/{id}/carteiras")
    @Transactional
    public ResponseEntity<?> associarCarteira(@PathVariable String id, @RequestBody
    @Valid AssociarCarteiraForm form, UriComponentsBuilder uriBuilder) {

        Cartao cartao = cartaoRepository.findByNumero(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado."));


        Carteira carteira = form.toModel(cartao);

        if (cartao.ehAssociado(carteira)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já está associada ao cartão");
        }

        try {
            servicoAssociaCartaoACarteira.associar(cartao.getNumero(), form);

            manager.persist(carteira);

            URI uri = uriBuilder.path("/cartoes/{numero}/carteiras/{id}").build(id, carteira.getId());

            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível associar carteira.");
        }
    }
}
