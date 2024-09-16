package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;


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
        var usuario = umUsuario().comEmail(email).build();

        when(usuarioRepository.buscarPorEmail(email))
                .thenReturn(Optional.of(usuario));

        var validationException = assertThrows(ValidationException.class,
                () -> usuarioService.salvar(usuario));

        assertEquals(String.format("Já existe um usuário com o email %s", email), validationException.getMessage());

        verify(usuarioRepository, never()).salvar(usuario);
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

