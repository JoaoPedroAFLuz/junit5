package br.com.joaopedroafluz.barriga.infra;

import br.com.joaopedroafluz.barriga.domain.Usuario;
import br.com.joaopedroafluz.barriga.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder.umUsuario;

public class UsuarioInMemoryRepository implements UsuarioRepository {

    private final List<Usuario> usuarios;
    private Long currentId;

    public UsuarioInMemoryRepository() {
        this.usuarios = new ArrayList<>();
        this.currentId = 0L;
        salvar(umUsuario().comId(null).comNome("Usuário #1").comEmail("usuario@email.com").build());
    }


    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        var novoUsuario = umUsuario()
                .comId(++currentId)
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
                .build();

        usuarios.add(novoUsuario);

        return novoUsuario;
    }

}
