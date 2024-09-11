package br.com.joaopedroafluz.barriga.domain;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.com.joaopedroafluz.barriga.domain.builders.ContaBuilder.umaConta;
import static br.com.joaopedroafluz.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    public void deveCriarContaValida() {
        var conta = umaConta().build();

        assertAll("Criação de conta",
                () -> assertNotNull(conta),
                () -> assertEquals(1L, conta.getId()),
                () -> assertEquals("Minha conta", conta.getNome()),
                () -> assertEquals(umUsuario().build(), conta.getUsuario()));
    }

    @ParameterizedTest(name = "[{index}] - {3}")
    @MethodSource(value = "dataProvider")
    public void deveValidarCamposObrigatorios(Long id, String nome, Usuario usuario, String mensagem) {
        var validationException = assertThrows(ValidationException.class,
                () -> umaConta().comId(id).comNome(nome).comUsuario(usuario).build());

        assertEquals(mensagem, validationException.getMessage());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, umUsuario().build(), "Nome é obrigatório"),
                Arguments.of(1L, "Minha conta", null, "Usuario é obrigatório"));
    }

}
