package com.projetoBFF.bff_agendador_tarefas.controller;


import com.projetoBFF.bff_agendador_tarefas.business.TarefaService;
import com.projetoBFF.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.projetoBFF.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.projetoBFF.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = " salvar e modificar tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar tarefas", description = "criar tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "tarefa já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO tarefasDTO,
                                                    @RequestHeader(value = "Authorization" , required = false) String token){

        return ResponseEntity.ok(tarefaService.gravarTarefa(token,tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas por periodo", description = "busca tarefa por certo periodo")
    @ApiResponse(responseCode = "200", description = "tarefa encontrada sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTO>> buscaListaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal ,
                                                                        @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadas(dataInicial,dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar  lista de tarefas por email", description = "busca tarefas por email")
    @ApiResponse(responseCode = "200", description = "lista de tarefas encontrada sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTO>> buscarListaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefas ", description = "deleta a tarefa por id")
    @ApiResponse(responseCode = "200", description = "tarefa deletada sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarPorId(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.deletarPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status da tarefa", description = "altera o status da tarefa por id")
    @ApiResponse(responseCode = "200", description = "tarefa status alterafa")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTO> alterarStatus(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                    @RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok().body(tarefaService.alterarStatus(statusNotificacaoEnum, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera a tarefa ", description = "alteracao de tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<TarefasDTO> alterarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                         @RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok().body(tarefaService.updateTarefa(id, tarefasDTO, token));
    }
}
