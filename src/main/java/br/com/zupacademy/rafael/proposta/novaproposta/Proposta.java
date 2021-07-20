package br.com.zupacademy.rafael.proposta.novaproposta;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @Positive
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    };

    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
                    @NotBlank String endereco, @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void situacao(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void adquire(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
                + endereco + ", salario=" + salario + ", status=" + status + ", cartao=" + cartao + "]";
    }

}
