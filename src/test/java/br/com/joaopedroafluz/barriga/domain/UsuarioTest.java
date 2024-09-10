package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder;
import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest(name = "[{index}] - {4}")
    @CsvSource(value = {
            "1, NULL, joao.pedro.luz@hotmail.com, 123456789, Nome é obrigatório",
            "1, João, NULL, 123456789, Email é obrigatório",
            "1, João, joao.pedro.luz@hotmail.com, NULL, Senha é obrigatória"}, nullValues = "NULL")
    public void deveValidarCamposObrigatorios(Long id, String nome, String email, String senha, String mensagem) {
        var validationException = assertThrows(ValidationException.class,
                () -> UsuarioBuilder.umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).build());

        assertEquals(mensagem, validationException.getMessage());
    }

}
