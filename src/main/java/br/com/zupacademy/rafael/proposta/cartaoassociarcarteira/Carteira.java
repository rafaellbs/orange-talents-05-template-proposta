package br.com.zupacademy.rafael.proposta.cartaoassociarcarteira;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {}

    public Carteira(@NotBlank String nome, @NotBlank @Email String email, @NotNull Cartao cartao) {
        this.nome = nome;
        this.email = email;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carteira other = (Carteira) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Carteira [id=" + id + ", nome=" + nome + ", email=" + email + ", cartao=" + cartao + "]";
    }
}
