package br.ufmg.es.tp1.findhelpbackend.services.chat;

import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import br.ufmg.es.tp1.findhelpbackend.repositories.chat.IChatRepository;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService implements IChatService {

    private final IChatRepository chatRepository;
    private final IUsuarioService usuarioService;

    public ChatService(IChatRepository chatRepository, IUsuarioService usuarioService) {
        this.chatRepository = chatRepository;
        this.usuarioService = usuarioService;
    }

    public UUID salvarMensagemConversa(UUID idRemetente, UUID idDestinatario, String conteudo) {
        if(idRemetente == null || idDestinatario == null || conteudo == null) {
            throw new ParametroInvalidoException();
        }
        Usuario remetente = usuarioService.buscarUsuario(idRemetente);
        Usuario destinatario = usuarioService.buscarUsuario(idDestinatario);
        Mensagem novaMensagem = new Mensagem(UUID.randomUUID(), conteudo, remetente, destinatario, LocalDateTime.now());
        chatRepository.save(novaMensagem);
        return novaMensagem.getId();
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

    @Override
    public List<Usuario> buscarHistoricoConversas(UUID idUsuario) {
        if(idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        return null;
    }
}
