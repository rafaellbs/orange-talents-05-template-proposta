package br.com.zupacademy.rafael.proposta.cadastroavisoviagem;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;
import br.com.zupacademy.rafael.proposta.criarcartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/cartoes/{id}/avisos/viagens")
    @Transactional
    public ResponseEntity<?> avisoViagem(@PathVariable String id, @RequestBody @Valid AvisoViagemForm form,
                                         HttpServletRequest request) {

        Optional<Cartao> possivelCartao = cartaoRepository.findByNumero(id);

        if (possivelCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String ipCliente = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        AvisoViagem avisoViagem = form.toModel(possivelCartao.get(), ipCliente, userAgent);

        manager.persist(avisoViagem);

        return ResponseEntity.ok().body(avisoViagem.toString());
    }
}

