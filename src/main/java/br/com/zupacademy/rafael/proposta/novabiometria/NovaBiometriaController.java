package br.com.zupacademy.rafael.proposta.novabiometria;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NovaBiometriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cartoes/{id}/biometrias")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable Long id,
                                       @Valid @RequestBody NovaBiometriaRequest request, UriComponentsBuilder uriBuilder) {
        Cartao cartao = manager.find(Cartao.class, id);

        if (cartao == null) {
            Map<String, String> response = new HashMap<>();
            response.put("erro", "cartao nao encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Biometria novaBiometria = request.toModel(cartao);

        manager.persist(novaBiometria);

        URI uri = uriBuilder.path("/{id}").build(novaBiometria.getId());

        return ResponseEntity.created(uri).build();
    }
}
