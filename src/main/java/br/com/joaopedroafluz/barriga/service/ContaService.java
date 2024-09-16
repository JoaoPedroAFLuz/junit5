package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.Conta;
import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.external.ContaEvent;
import br.com.joaopedroafluz.barriga.repository.ContaRepository;

import java.util.Optional;

public class ContaService {

    private final ContaEvent contaEvent;
    private final ContaRepository contaRepository;

    public ContaService(ContaEvent contaEvent,
                        ContaRepository contaRepository) {
        this.contaEvent = contaEvent;
        this.contaRepository = contaRepository;
    }


    public Optional<Conta> buscarContaPorUsuarioIdENome(Long usuarioId, String nome) {
        return contaRepository.buscarContaPorUsuarioIdENome(usuarioId, nome);
    }

    public Conta salvar(Conta conta) {
        buscarContaPorUsuarioIdENome(conta.getUsuario().getId(), conta.getNome())
                .ifPresent((contaExistente) -> {
                    throw new ValidationException(String.format("Usuário já possui conta com nome %s",
                            conta.getNome()));
                });

        var contaSalva = contaRepository.salvar(conta);

        try {
            contaEvent.expedir(contaSalva, ContaEvent.EventType.CRIADA);
        } catch (Exception e) {
            contaRepository.remover(contaSalva);
            throw new RuntimeException("Não foi possível criar a conta, tente novamente mais tarde");
        }

        return contaSalva;
    }

}
