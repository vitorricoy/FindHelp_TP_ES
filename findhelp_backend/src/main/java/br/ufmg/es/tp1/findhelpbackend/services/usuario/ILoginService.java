package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import java.util.UUID;

public interface ILoginService {
    UUID logarUsuario(String nomeUsuario, String senha);
}
