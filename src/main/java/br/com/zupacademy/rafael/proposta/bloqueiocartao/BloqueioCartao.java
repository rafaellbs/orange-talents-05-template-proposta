package br.com.zupacademy.rafael.proposta.bloqueiocartao;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "cartoes_bloqueados")
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Cartao cartao;

    @NotBlank
    private String enderecoIp;

    @NotBlank
    private String userAgent;

    @NotNull
    private LocalDateTime instante;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(@NotNull Cartao cartao, @NotBlank String enderecoIp, @NotBlank String userAgent) {
        super();
        this.cartao = cartao;
        this.enderecoIp = enderecoIp;
        this.userAgent = userAgent;
        this.instante = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "BloqueioCartao [id=" + id + ", cartao=" + cartao + ", enderecoIp=" + enderecoIp + ", userAgent="
                + userAgent + ", instante=" + instante + "]";
    }

}
