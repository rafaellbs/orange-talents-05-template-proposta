package br.com.zupacademy.rafael.proposta.bloqueiocartao;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;
import br.com.zupacademy.rafael.proposta.criarcartao.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class BloqueioCartaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ServicoBloqueioCartao servicoBloqueioCartao;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PutMapping("/cartoes/{id}/bloqueios")
    @Transactional
    public ResponseEntity<?> bloquearCartao(@PathVariable String id, HttpServletRequest request) {
        Optional<Cartao> cartao = cartaoRepository.findByNumero(id);

        if (cartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (cartao.get().estaBloqueado()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cartão já está bloqueado");
        }

        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        BloqueioCartao bloqueio = new BloqueioCartao(cartao.get(), ip, userAgent);
        try {
            servicoBloqueioCartao.notifica(cartao.get().getNumero(), new BloqueioCartaoDTO("apiproposta"));
            cartao.get().bloquear();

            manager.persist(bloqueio);
            return ResponseEntity.ok().build();
        } catch (FeignException.FeignClientException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Não foi possível bloquear o cartão");
        }
    }
}
