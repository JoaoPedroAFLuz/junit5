package br.com.joaopedroafluz.barriga.infra;

import br.com.joaopedroafluz.barriga.domain.Usuario;
import br.com.joaopedroafluz.barriga.repository.UsuarioRepository;

import java.util.Optional;

import static br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder.umUsuario;

public class UsuarioDummyRepository implements UsuarioRepository {

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return "dummy@email.com".equals(email)
                ? Optional.of(umUsuario().comEmail(email).build())
                : Optional.empty();
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return umUsuario()
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
                .build();
    }

}
