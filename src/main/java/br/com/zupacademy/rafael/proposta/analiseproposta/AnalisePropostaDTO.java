package br.com.zupacademy.rafael.proposta.analiseproposta;

import br.com.zupacademy.rafael.proposta.novaproposta.Proposta;
import br.com.zupacademy.rafael.proposta.validacao.ValidarCPFeCNPJ;
import br.com.zupacademy.rafael.proposta.validacao.ExistsId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnalisePropostaDTO {

    @NotBlank
    @ValidarCPFeCNPJ
    private String documento;

    @NotBlank
    private String nome;

    @NotNull
    @ExistsId(domainClass = Proposta.class, fieldName = "id")
    private Long idProposta;

    public AnalisePropostaDTO(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "AnalisePropostaRequest [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta + "]";
    }

}
