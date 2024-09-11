package br.com.joaopedroafluz.barriga.repository;

import br.com.joaopedroafluz.barriga.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> buscarPorEmail(String email);

    Usuario salvar(Usuario usuario);

}
