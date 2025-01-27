package br.com.zupacademy.rafael.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);

    @Query(value = "select * from proposta p where p.status = 'ELEGIVEL' and isnull(p.cartao_id)", nativeQuery = true)
    List<Proposta> findByStatusElegivelAndPropostaIsNull();
}