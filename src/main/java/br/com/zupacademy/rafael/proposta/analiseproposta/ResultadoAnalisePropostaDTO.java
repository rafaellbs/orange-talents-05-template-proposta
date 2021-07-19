package br.com.zupacademy.rafael.proposta.analiseproposta;

import javax.validation.constraints.NotBlank;

public class ResultadoAnalisePropostaDTO {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String resultadoSolicitacao;

    @NotBlank
    private String idProposta;

    @Deprecated
    public ResultadoAnalisePropostaDTO() {}

    public ResultadoAnalisePropostaDTO(@NotBlank String documento, @NotBlank String nome,
                                       @NotBlank String resultadoSolicitacao, @NotBlank String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }


    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "SolicitacaoResponse [documento=" + documento + ", nome=" + nome + ", resultadoSolicitacao="
                + resultadoSolicitacao + ", idProposta=" + idProposta + "]";
    }
}
