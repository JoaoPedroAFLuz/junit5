package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder;
import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void deveCriarUsuarioValido() {
        var usuario = UsuarioBuilder.umUsuario()
                .comId(1L)
                .comNome("João")
                .comEmail("joao.pedro.luz@hotmail.com")
                .comSenha("123456789")
                .build();

        assertAll("Criação de usuário",
                () -> assertNotNull(usuario),
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("João", usuario.getNome()),
                () -> assertEquals("joao.pedro.luz@hotmail.com", usuario.getEmail()),
                () -> assertEquals("123456789", usuario.getSenha()));
    }

    @Test
    public void deveRejeitarCriacaoUsuarioSemNome() {
        var validationException = assertThrows(ValidationException.class,
                () -> UsuarioBuilder.umUsuario().comNome(null).build());

        assertEquals("Nome é obrigatório", validationException.getMessage());
    }

    @Test
    public void deveRejeitarCriacaoUsuarioSemEmail() {
        var validationException = assertThrows(ValidationException.class,
                () -> UsuarioBuilder.umUsuario().comEmail(null).build());

        assertEquals("Email é obrigatório", validationException.getMessage());
    }

    @Test
    public void deveRejeitarCriacaoUsuarioSemSenha() {
        var validationException = assertThrows(ValidationException.class,
                () -> UsuarioBuilder.umUsuario().comSenha(null).build());

        assertEquals("Senha é obrigatória", validationException.getMessage());
    }

}
