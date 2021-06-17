package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import br.ufmg.es.tp1.findhelpbackend.models.Usuario;

import java.util.UUID;

public interface IUsuarioService {
    UUID salvarDadosUsuario(String nome, String nomeUsuario, String senha, boolean psicologo);
    Usuario buscarUsuario(UUID idUsuario);
}