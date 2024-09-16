package br.com.joaopedroafluz.barriga.service;

import br.com.joaopedroafluz.barriga.domain.Conta;
import br.com.joaopedroafluz.barriga.domain.exceptions.ValidationException;
import br.com.joaopedroafluz.barriga.repository.ContaRepository;

import java.util.Optional;

public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }


    public Optional<Conta> buscarContaPorUsuarioIdENome(Long usuarioId, String nome) {
        return contaRepository.buscarContaPorUsuarioIdENome(usuarioId, nome);
    }

    public Conta salvar(Conta conta) {
        buscarContaPorUsuarioIdENome(conta.getUsuario().getId(), conta.getNome())
                .ifPresent((contaExistente) -> {
            throw new ValidationException(String.format("Usuário já possui conta com nome %s", conta.getNome()));
        });

        return contaRepository.salvar(conta);
    }

}
