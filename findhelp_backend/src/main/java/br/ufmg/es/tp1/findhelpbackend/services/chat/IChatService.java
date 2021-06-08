package br.ufmg.es.tp1.findhelpbackend.services.chat;

import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;

import java.util.List;
import java.util.UUID;

public interface IChatService {

    UUID salvarMensagemConversa(UUID idRecipiente, UUID idRemetente, String conteudo);
    List<Mensagem> buscarMensagensConversa(UUID idRecipiente, UUID idRemetente);
}
