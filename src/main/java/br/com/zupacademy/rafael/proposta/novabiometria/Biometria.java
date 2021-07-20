package br.com.zupacademy.rafael.proposta.novabiometria;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fingerprint;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotNull
    private LocalDate instante;

    @Deprecated
    public Biometria() {
    }

    public Biometria(@NotBlank String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
        this.instante = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Biometria [id=" + id + ", fingerprint=" + fingerprint + ", cartao=" + cartao + ", instante=" + instante
                + "]";
    }

}
