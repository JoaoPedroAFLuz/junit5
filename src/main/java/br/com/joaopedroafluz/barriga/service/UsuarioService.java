package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.Usuario;
import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.repository.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.buscarPorEmail(email);
    }

    public Usuario salvar(Usuario usuario) {
        buscarPorEmail(usuario.getEmail()).ifPresent((usuarioExistente) -> {
            throw new ValidationException(String.format("Já existe um usuário com o email %s", usuario.getEmail()));
        });

        return usuarioRepository.salvar(usuario);
    }

}
