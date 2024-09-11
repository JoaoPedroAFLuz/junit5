package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.infra.UsuarioDummyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    private UsuarioService usuarioService;


    @BeforeEach
    public void beforeEach() {
        usuarioService = new UsuarioService(new UsuarioDummyRepository());
    }

    @Test
    public void deveCriarUsuarioComSucesso() {
        var usuario = umUsuario().build();

        var usuarioSalvo = usuarioService.salvar(usuario);

        assertAll("Criação de usuário",
                () -> assertNotNull(usuarioSalvo),
                () -> assertNotNull(usuarioSalvo.getId()));
    }

    @Test
    public void naoDeveCriarUsuarioQuandoEmailJaEmUso() {
        var validationException = assertThrows(ValidationException.class,
                () -> usuarioService.salvar(umUsuario().comEmail("dummy@email.com").build()));

        assertEquals("Já existe um usuário com o email dummy@email.com", validationException.getMessage());
    }

    @Test
    public void deveBuscarUsuarioPorEmail() {
        var usuarioEncontrado = usuarioService.buscarPorEmail("dummy@email.com");

        assertAll("Busca por email",
                () -> assertNotNull(usuarioEncontrado),
                () -> assertEquals("dummy@email.com", usuarioEncontrado.get().getEmail()));
    }

}

