package com.projetoBFF.bff_agendador_tarefas.controller;


import com.projetoBFF.bff_agendador_tarefas.business.UsuarioService;
import com.projetoBFF.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "cadastro e login de usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuarios", description = "cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuarios", description = "login de usuario")
    @ApiResponse(responseCode = "200", description = "usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuarios por email", description = "buscar dados de usuario")
    @ApiResponse(responseCode = "200", description = "usuario encontrado")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuarios por id", description = "deleta um usuario")
    @ApiResponse(responseCode = "200", description = "usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuarios", description = "atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                           @RequestHeader(name = "Authorization", required = false) String token ){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token,usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar  endereço de usuario", description = "atualiza por endereço")
    @ApiResponse(responseCode = "200", description = "endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "endereco não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam("id") Long id,
                                                         @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone de usuario", description = "atualiza por telefone")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestParam("id") Long id,
                                                         @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereco de usuario", description = "atualiza endereco de usuario")
    @ApiResponse(responseCode = "200", description = "endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "endereco não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salvar telefone de usuario", description = "salva telefone")
    @ApiResponse(responseCode = "200", description = "telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }
}
