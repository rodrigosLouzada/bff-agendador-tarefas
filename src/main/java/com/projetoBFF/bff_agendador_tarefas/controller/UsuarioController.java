package com.projetoBFF.bff_agendador_tarefas.controller;


import com.projetoBFF.bff_agendador_tarefas.business.UsuarioService;
import com.projetoBFF.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization") String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                           @RequestHeader("Authorization") String token ){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token,usuarioDTO));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam("id") Long id,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestParam("id") Long id,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }
}
