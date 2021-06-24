package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import br.ufmg.es.tp1.findhelpbackend.models.HistoricoConversa;

import java.util.List;
import java.util.UUID;

public interface ILoginService {
    UUID logarUsuario(String nomeUsuario, String senha);
    List<HistoricoConversa> buscarPsicologosOnline();
    boolean deslogarUsuario(UUID IDUsuario);
}
