package br.ufmg.es.tp1.findhelpbackend.services.chat;

import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import br.ufmg.es.tp1.findhelpbackend.repositories.chat.IChatRepository;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Mensagem> enviadasPeloUsuario = chatRepository.findByDestinatarioAndRemetente(idContato, idUsuario);
        List<Mensagem> recebidasPeloUsuario = chatRepository.findByDestinatarioAndRemetente(idUsuario, idContato);
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
        List<Mensagem> mensagensEnviadas = chatRepository.findByRemetente(idUsuario);
        List<Mensagem> mensagensRecebidas = chatRepository.findByDestinatario(idUsuario);
        List<Usuario> usuarios = new ArrayList<>();
        for(Mensagem mensagem : mensagensEnviadas) {
            usuarios.add(mensagem.getDestinatario());
        }
        for(Mensagem mensagem : mensagensRecebidas) {
            usuarios.add(mensagem.getRemetente());
        }
        usuarios = usuarios.stream().distinct().collect(Collectors.toList());
        return usuarios;
    }
}
