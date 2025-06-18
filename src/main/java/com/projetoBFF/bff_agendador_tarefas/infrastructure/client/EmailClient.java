package com.projetoBFF.bff_agendador_tarefas.infrastructure.client;

import com.projetoBFF.bff_agendador_tarefas.business.dto.TarefasDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao" , url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviaremail(@RequestBody TarefasDTO tarefasDTO);
}
