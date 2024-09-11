package br.com.joaopedroafluz.barriga.domain.builders;

import br.com.joaopedroafluz.barriga.domain.Conta;
import br.com.joaopedroafluz.barriga.domain.Usuario;

public class ContaBuilder {
    private Long id;
    private String nome;
    private Usuario usuario;

    private ContaBuilder(){}

    public static ContaBuilder umaConta() {
        ContaBuilder builder = new ContaBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(ContaBuilder builder) {
        builder.id = 1L;
        builder.nome = "Minha conta";
        builder.usuario = UsuarioBuilder.umUsuario().build();
    }

    public ContaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public ContaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ContaBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Conta build() {
        return new Conta(id, nome, usuario);
    }

}