package br.com.zupacademy.rafael.proposta.criarcartao;

import br.com.zupacademy.rafael.proposta.novabiometria.Biometria;
import br.com.zupacademy.rafael.proposta.novaproposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;

    @NotBlank
    private String titular;

    @NotNull
    private BigDecimal limite;

    @NotNull
    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias;

    @Enumerated(EnumType.STRING)
    private StatusCartao status;

    @Deprecated
    public Cartao() {
    }

    public Cartao(@NotBlank String numero, @NotBlank String titular, @NotNull BigDecimal limite,
                  @NotNull Proposta proposta) {
        super();
        this.numero = numero;
        this.titular = titular;
        this.limite = limite;
        this.proposta = proposta;
        this.biometrias = new ArrayList<Biometria>();
        this.status = StatusCartao.NAO_BLOQUEADO;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public void inserirNova(Biometria biometria) {
        this.biometrias.add(biometria);
    }

    public boolean estaBloqueado() {
        if (status.equals(StatusCartao.BLOQUEADO)) {
            return true;
        }

        return false;
    }

    public void bloquear() {
        this.status = StatusCartao.BLOQUEADO;
    }

    @Override
    public String toString() {
        return "Cartao [id=" + id + ", numero=" + numero + ", titular=" + titular + ", limite=" + limite + "]";
    }

}
