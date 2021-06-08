package br.ufmg.es.tp1.findhelpbackend.services.chat;

import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.repositories.chat.IChatRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService implements IChatService {

    private final IChatRepository chatRepository;

    public ChatService(IChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public UUID salvarMensagemConversa(UUID idUsuario, UUID idContato, String conteudo) {
        if(idUsuario == null || idContato == null || conteudo == null) {
            throw new ParametroInvalidoException();
        }
        return null;
    }

    public List<Mensagem> buscarMensagensConversa(UUID idUsuario, UUID idContato) {
        if(idContato == null || idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        List<Mensagem> enviadasPeloUsuario = chatRepository.findByRecipenteAndRemetente(idContato, idUsuario);
        List<Mensagem> recebidasPeloUsuario = chatRepository.findByRecipenteAndRemetente(idUsuario, idContato);
        List<Mensagem> todasMensagens = enviadasPeloUsuario;
        todasMensagens.addAll(recebidasPeloUsuario);
        todasMensagens.sort(Comparator.comparing(Mensagem::getHorarioEnvio));
        return todasMensagens;
    }
}
