package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;

public class Usuario {

    private Long id;
    private String name;
    private String email;
    private String password;

    public Usuario(Long id, String name, String email, String password) {
        if (name == null) throw new ValidationException("Nome não pode ser nulo");
        if (email == null) throw new ValidationException("Email não pode ser nulo");
        if (password == null) throw new ValidationException("Senha não pode ser nula");

        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
