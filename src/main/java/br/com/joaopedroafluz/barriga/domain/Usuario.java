package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;

import java.util.Objects;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        if (nome == null) throw new ValidationException("Nome é obrigatório");
        if (email == null) throw new ValidationException("Email é obrigatório");
        if (senha == null) throw new ValidationException("Senha é obrigatória");

        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(nome, usuario.nome)
                && Objects.equals(email, usuario.email)
                && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha);
    }

}
