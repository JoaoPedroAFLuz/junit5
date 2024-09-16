package br.com.joaopedroafluz.barriga.external;

import br.com.joaopedroafluz.barriga.domain.Conta;

public interface ContaEvent {

    enum EventType {CRIADA, ATUALIZADA, REMOVIDA}

    void expedir(Conta conta, EventType eventType) throws Exception;

}
