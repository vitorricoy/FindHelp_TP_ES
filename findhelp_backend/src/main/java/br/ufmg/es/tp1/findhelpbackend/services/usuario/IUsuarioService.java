package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import java.util.UUID;

public interface IUsuarioService {
    UUID salvarDadosUsuario(String nome, String nomeUsuario, String senha, boolean psicologo);
}