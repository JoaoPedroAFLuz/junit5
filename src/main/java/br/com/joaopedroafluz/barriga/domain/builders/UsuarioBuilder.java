package br.com.joaopedroafluz.barriga.domain.builders;

import br.com.joaopedroafluz.barriga.domain.Usuario;

public class UsuarioBuilder {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(UsuarioBuilder builder) {
        builder.id = 1L;
        builder.nome = "João";
        builder.email = "joao.pedro.luz@hotmail.com";
        builder.senha = "123456789";
    }

    public UsuarioBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nome, email, senha);
    }

}