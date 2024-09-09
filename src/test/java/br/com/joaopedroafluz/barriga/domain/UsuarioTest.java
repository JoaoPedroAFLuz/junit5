package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void deveCriarUsuarioValido() {
        var usuario = new Usuario(1L, "João", "joao.pedro.luz@hotmail.com", "123");

        assertAll("Criação de usuário",
                () -> assertNotNull(usuario),
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("João", usuario.getName()),
                () -> assertEquals("joao.pedro.luz@hotmail.com", usuario.getEmail()),
                () -> assertEquals("123", usuario.getPassword()));
    }

    @Test
    public void deveRejeitarCriacaoUsuarioSemNome() {
        var validationException = assertThrows(ValidationException.class,
                () -> new Usuario(1L, null, "joao.pedro.luz@hotmail.com", "123"));

        assertEquals("Nome não pode ser nulo", validationException.getMessage());
    }

    @Test
    public void deveRejeitarCriacaoUsuarioSemEmail() {
        var validationException = assertThrows(ValidationException.class,
                () -> new Usuario(1L, "João", null, "123"));

        assertEquals("Email não pode ser nulo", validationException.getMessage());
    }

    @Test
    public void deveRejeitarCriacaoUsuarioSemSenha() {
        var validationException = assertThrows(ValidationException.class,
                () -> new Usuario(1L, "João", "joao.pedro.luz@hotmail.com", null));

        assertEquals("Senha não pode ser nula", validationException.getMessage());
    }

}
