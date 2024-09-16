package br.com.joaopedroafluz.barriga.repository;

import br.com.joaopedroafluz.barriga.domain.Conta;

import java.util.Optional;

public interface ContaRepository {

    Optional<Conta> buscarContaPorUsuarioIdENome(Long usuarioId, String nome);

    Conta salvar(Conta conta);

}
