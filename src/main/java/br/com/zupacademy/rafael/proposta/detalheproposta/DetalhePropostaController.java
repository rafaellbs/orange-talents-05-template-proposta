package br.com.zupacademy.rafael.proposta.detalheproposta;

import br.com.zupacademy.rafael.proposta.novaproposta.Proposta;
import br.com.zupacademy.rafael.proposta.novaproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetalhePropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> detalhe(@PathVariable Long id) {
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);

        if (possivelProposta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada");
        }

        return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }
}
