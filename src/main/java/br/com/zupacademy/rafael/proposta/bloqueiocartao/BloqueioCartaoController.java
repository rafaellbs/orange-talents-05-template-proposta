package br.com.zupacademy.rafael.proposta.bloqueiocartao;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
public class BloqueioCartaoController {

    @PersistenceContext
    private EntityManager manager;

    @PutMapping("/cartoes/{id}/bloqueio")
    @Transactional
    public ResponseEntity<?> bloquearCartao(@PathVariable Long id, HttpServletRequest request) {
        Cartao cartao = manager.find(Cartao.class, id);

        if (cartao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (cartao.estaBloqueado()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cartão já está bloqueado");
        }

        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        BloqueioCartao bloqueio = new BloqueioCartao(cartao, ip, userAgent);
        cartao.bloquear();

        manager.persist(bloqueio);

        return ResponseEntity.ok().build();
    }
}
