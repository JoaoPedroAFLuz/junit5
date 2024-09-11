package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;

public class Conta {

    private Long id;
    private String nome;
    private Usuario usuario;

    public Conta(Long id, String nome, Usuario usuario) {
        if (nome == null) throw new ValidationException("Nome é obrigatório");
        if (usuario == null) throw new ValidationException("Usuario é obrigatório");

        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
