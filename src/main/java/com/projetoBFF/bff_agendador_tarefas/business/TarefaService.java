package com.projetoBFF.bff_agendador_tarefas.business;


import com.projetoBFF.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.projetoBFF.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.projetoBFF.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient tarefasClient;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO){

        return tarefasClient.gravarTarefas(tarefasDTO, token);
    }


    public List<TarefasDTO> buscarTarefasAgendadas(LocalDateTime dataInicial, LocalDateTime dataFinal,  String token){
        return tarefasClient.buscaListaTarefasPorPeriodo(dataInicial, dataFinal,  token);
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token){
        return tarefasClient.buscarListaTarefasPorEmail(token);
    }

    public void deletarPorId(String id, String token){
         tarefasClient.deletarPorId(id, token);
    }

    public TarefasDTO alterarStatus(StatusNotificacaoEnum statusNotificacaoEnum, String id, String token){

        return  tarefasClient.alterarStatus(statusNotificacaoEnum, id, token);
    }

    public TarefasDTO updateTarefa(String id, TarefasDTO tarefasDTO, String token){
        return tarefasClient.alterarTarefa(tarefasDTO, id, token);
    }
}
