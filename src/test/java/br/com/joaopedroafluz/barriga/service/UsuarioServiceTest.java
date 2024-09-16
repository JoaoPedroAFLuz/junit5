package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void beforeEach() {
        usuarioRepository = mock(UsuarioRepository.class);

        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    public void deveSalvarUsuarioComSucesso() {
        var usuario = umUsuario().comId(null).build();

        when(usuarioRepository.salvar(usuario))
                .thenReturn(umUsuario().build());

        var usuarioSalvo = usuarioService.salvar(usuario);

        assertAll("Criação de usuário",
                () -> assertNotNull(usuarioSalvo),
                () -> assertEquals(1L, usuarioSalvo.getId()),
                () -> assertEquals(usuario, usuarioSalvo));

        verify(usuarioRepository).buscarPorEmail(usuario.getEmail());
    }

    @Test
    public void deveLancarExcecaoQuandoTentarCadastrarUsuarioComEmailJaCadastrado() {
        var email = "joao.pedro.luz@hotmail.com";

        when(usuarioRepository.buscarPorEmail(email))
                .thenReturn(Optional.of(umUsuario().comEmail(email).build()));

        var validationException = assertThrows(ValidationException.class,
                () -> usuarioService.salvar(umUsuario().comEmail(email).build()));

        assertEquals(String.format("Já existe um usuário com o email %s", email), validationException.getMessage());
    }

    @Test
    public void deveRetornarUsuarioPorEmail() {
        var email = "joao.pedro.luz@hotmail.com";

        when(usuarioRepository.buscarPorEmail(email))
                .thenReturn(Optional.of(umUsuario().comEmail(email).build()));

        var usuario = usuarioService.buscarPorEmail(email);

        assertAll("Busca de usuário por email",
                () -> assertTrue(usuario.isPresent()),
                () -> assertNotNull(usuario.get().getId()),
                () -> assertEquals(email, usuario.get().getEmail()));
    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioNaoEncontrado() {
        var emailNaoCadastrado = "email.nao.cadastrado@email.com";

        var usuario = usuarioService.buscarPorEmail(emailNaoCadastrado);

        assertTrue(usuario.isEmpty());

        verify(usuarioRepository, times(1)).buscarPorEmail(emailNaoCadastrado);
    }

}

