package com.projetoBFF.bff_agendador_tarefas.business;

import com.projetoBFF.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.projetoBFF.bff_agendador_tarefas.infrastructure.client.EmailClient;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;
    public void enviaEmail(TarefasDTO tarefasDTO) {
        emailClient.enviaremail(tarefasDTO);
    }
}
