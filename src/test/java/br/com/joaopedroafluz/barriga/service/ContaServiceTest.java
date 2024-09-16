package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.joaopedroafluz.barriga.domain.builders.ContaBuilder.umaConta;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;


    @Test
    public void deveSalvarPrimeiraContaComSucesso() {
        var novaConta = umaConta().comId(null).build();

        when(contaRepository.salvar(novaConta))
                .thenReturn(umaConta().build());

        var contaSalva = contaService.salvar(novaConta);

        assertAll("Criação de novaConta",
                () -> assertNotNull(contaSalva),
                () -> assertEquals(1L, contaSalva.getId()),
                () -> assertEquals(novaConta, contaSalva));

        verify(contaRepository).buscarContaPorUsuarioIdENome(novaConta.getUsuario().getId(), novaConta.getNome());
    }

    @Test
    public void deveSalvarDemaisContasComSucesso() {
        var nomeConta = "Outra Conta";
        var novaConta = umaConta().comId(null).comNome(nomeConta).build();

        when(contaRepository.buscarContaPorUsuarioIdENome(novaConta.getUsuario().getId(), nomeConta))
                .thenReturn(Optional.empty());

        when(contaRepository.salvar(novaConta))
                .thenReturn(umaConta().comNome(nomeConta).build());

        var contaSalva = contaService.salvar(novaConta);

        assertAll("Criação de novaConta",
                () -> assertNotNull(contaSalva),
                () -> assertEquals(1L, contaSalva.getId()),
                () -> assertEquals(novaConta, contaSalva));

        verify(contaRepository).buscarContaPorUsuarioIdENome(novaConta.getUsuario().getId(), novaConta.getNome());
    }

    @Test
    public void deveLancarExcecaoQuandoTentarCadastrarContaParaMesmoUsuarioComNomeRepetido() {
        var contaComNomeRepetido = umaConta().comId(null).comNome("Minha Conta").build();

        when(contaRepository.buscarContaPorUsuarioIdENome(contaComNomeRepetido.getUsuario().getId(),
                contaComNomeRepetido.getNome()))
                .thenReturn(Optional.of(umaConta().comNome("Minha Conta").build()));

        var validationException = assertThrows(ValidationException.class,
                () -> contaService.salvar(contaComNomeRepetido));

        assertEquals(String.format("Usuário já possui conta com nome %s", contaComNomeRepetido.getNome()),
                validationException.getMessage());

        verify(contaRepository, never()).salvar(contaComNomeRepetido);
    }

}
