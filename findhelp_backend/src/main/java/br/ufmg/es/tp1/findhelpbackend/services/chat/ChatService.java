package br.ufmg.es.tp1.findhelpbackend.services.chat;

import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;

import java.util.List;
import java.util.UUID;

public class ChatService implements IChatService {

    public UUID salvarMensagemConversa(UUID idRecipiente, UUID idRemetente, Mensagem conteudo) {
        return null;
    }

    public List<Mensagem> buscarMensagensConversa(UUID idRecipiente, UUID idRemetente) {
        return null;
    }
}
