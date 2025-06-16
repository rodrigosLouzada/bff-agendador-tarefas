package com.projetoBFF.bff_agendador_tarefas.infrastructure.client;

import com.projetoBFF.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.projetoBFF.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas" , url = "${agendador-tarefas.url}")
public interface TarefasClient {


    @PostMapping
    TarefasDTO gravarTarefas(@RequestBody TarefasDTO tarefasDTO,
                                                    @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefasDTO> buscaListaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal ,
                                                                        @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTO> buscarListaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTO alterarStatus(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTO alterarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                         @RequestParam("id") String id,
                                                         @RequestHeader("Authorization") String token);
}
