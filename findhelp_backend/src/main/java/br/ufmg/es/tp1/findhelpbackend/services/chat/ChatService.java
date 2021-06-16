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

    private Mensagem construirMensagem(String conteudo, Usuario remetente, Usuario destinatario) {
        return new Mensagem(UUID.randomUUID(), conteudo, remetente, destinatario, LocalDateTime.now());
    }


    public UUID salvarMensagemConversa(UUID idRemetente, UUID idDestinatario, String conteudo) {
        if(idRemetente == null || idDestinatario == null || conteudo == null) {
            throw new ParametroInvalidoException();
        }
        Usuario remetente = usuarioService.buscarUsuario(idRemetente);
        Usuario destinatario = usuarioService.buscarUsuario(idDestinatario);
        Mensagem novaMensagem = construirMensagem(conteudo, remetente, destinatario);
        chatRepository.save(novaMensagem);
        return novaMensagem.getId();
    }

    private List<Mensagem> combinarOrdenarMensagens(List<Mensagem> lista1, List<Mensagem> lista2) {
        return Stream.of(lista1, lista2)
                     .flatMap(Collection::stream)
                     .sorted(Comparator.comparing(Mensagem::getHorarioEnvio))
                     .collect(Collectors.toList());
    }

    public List<Mensagem> buscarMensagensConversa(UUID idUsuario, UUID idContato) {
        if(idContato == null || idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        List<Mensagem> enviadasPeloUsuario = chatRepository.findByDestinatarioAndRemetente(idContato, idUsuario);
        List<Mensagem> recebidasPeloUsuario = chatRepository.findByDestinatarioAndRemetente(idUsuario, idContato);
        List<Mensagem> todasMensagens = combinarOrdenarMensagens(enviadasPeloUsuario, recebidasPeloUsuario);
        return todasMensagens;
    }

    private List<Usuario> extrairUsuariosConversa(List<Mensagem> enviadas, List<Mensagem> recebidas) {
        List<Usuario> usuarios = new ArrayList<>();
        for(Mensagem mensagem : enviadas) {
            usuarios.add(mensagem.getDestinatario());
        }
        for(Mensagem mensagem : recebidas) {
            usuarios.add(mensagem.getRemetente());
        }
        return usuarios;
    }

    private List<Usuario> filtrarUsuariosDistintos(List<Usuario> usuarios) {
        return usuarios.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Usuario> buscarHistoricoConversas(UUID idUsuario) {
        if(idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        List<Mensagem> mensagensEnviadas = chatRepository.findByRemetente(idUsuario);
        List<Mensagem> mensagensRecebidas = chatRepository.findByDestinatario(idUsuario);
        List<Usuario> usuarios = extrairUsuariosConversa(mensagensEnviadas, mensagensRecebidas);
        usuarios = filtrarUsuariosDistintos(usuarios);
        return usuarios;
    }
}
