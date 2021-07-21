package br.com.zupacademy.rafael.proposta.cartaoassociarcarteira;

import br.com.zupacademy.rafael.proposta.criarcartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociarCarteiraForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String carteira;

    public AssociarCarteiraForm(@Email @NotBlank String email, @NotBlank String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    @Override
    public String toString() {
        return "AssociarCarteiraForm [email=" + email + ", carteira=" + carteira + "]";
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(carteira, email, cartao);
    }
}
