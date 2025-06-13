package com.projetoBFF.bff_agendador_tarefas.business;


import com.projetoBFF.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.projetoBFF.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.projetoBFF.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){

        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }



    public UsuarioDTO buscarUsuarioPorEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }



    public void deletaUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email,token);
    }


    public UsuarioDTO atualizarDadosUsuario(String token,UsuarioDTO dto){
        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO, String token){
        return usuarioClient.atualizarEndereco(enderecoDTO,id,token );
    }

    public TelefoneDTO atualizarTelefone(Long id, TelefoneDTO telefoneDTO, String token){
        return usuarioClient.atualizarTelefone(telefoneDTO, id, token);
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO enderecoDTO){

        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO telefoneDTO){
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }
}
