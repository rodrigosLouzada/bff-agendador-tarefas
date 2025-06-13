package com.projetoBFF.bff_agendador_tarefas.infrastructure.client;

import com.projetoBFF.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario" , url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String Token);


    @PostMapping
    UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                           @RequestHeader("Authorization") String token );

    @PutMapping("/endereco")
    EnderecoDTO atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam("id") Long id,
                                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizarTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestParam("id") Long id,
                                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                        @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                        @RequestHeader("Authorization") String token);

}
