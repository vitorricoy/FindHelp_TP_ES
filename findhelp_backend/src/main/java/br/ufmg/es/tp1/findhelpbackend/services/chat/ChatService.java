package br.ufmg.es.tp1.findhelpbackend.services.chat;

import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.HistoricoConversa;
import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import br.ufmg.es.tp1.findhelpbackend.repositories.chat.IChatRepository;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.data.util.Pair;
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
        return new Mensagem(UUID.randomUUID(), conteudo, remetente, destinatario, false, LocalDateTime.now());
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
        List<Mensagem> enviadasPeloUsuario = chatRepository.findByDestinatarioAndRemetente(usuarioService.buscarUsuario(idContato), usuarioService.buscarUsuario(idUsuario));
        List<Mensagem> recebidasPeloUsuario = chatRepository.findByDestinatarioAndRemetente(usuarioService.buscarUsuario(idUsuario), usuarioService.buscarUsuario(idContato));
        List<Mensagem> todasMensagens = combinarOrdenarMensagens(enviadasPeloUsuario, recebidasPeloUsuario);
        return todasMensagens;
    }

    public boolean marcarMensagensVistas(UUID idUsuario, UUID idContato) {
        if(idContato == null || idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        List<Mensagem> mensagensNaoVistas = chatRepository.
                findAllByVistaAndDestinatarioAndRemetente(false, usuarioService.buscarUsuario(idUsuario), usuarioService.buscarUsuario(idContato));

        for(Mensagem mensagem : mensagensNaoVistas) {
            mensagem.setVista(true);
        }

        chatRepository.saveAll(mensagensNaoVistas);
        return true;
    }

    Pair<UUID, String> construirPairUsuario(Usuario usuario) {
        return Pair.of(usuario.getId(), usuario.getNome());
    }

    private Map<Pair<UUID, String>, Integer> extrairUsuariosConversa(List<Mensagem> enviadas, List<Mensagem> recebidas) {
        Map<Pair<UUID, String>, Integer> usuariosNaoVistos = new HashMap<>();

        // Mensagens recebidas contam para a notificação de não visto
        for(Mensagem mensagem : recebidas) {
            Pair<UUID, String> pairUsuario = construirPairUsuario(mensagem.getRemetente());
            if(usuariosNaoVistos.containsKey(pairUsuario)) {
                Integer naoVistosAtual = usuariosNaoVistos.get(pairUsuario);
                if(!mensagem.isVista()) {
                    naoVistosAtual++;
                }
                usuariosNaoVistos.replace(pairUsuario, naoVistosAtual);
            } else {
                usuariosNaoVistos.put(pairUsuario, (mensagem.isVista())?0:1);
            }
        }

        for(Mensagem mensagem : enviadas) {
            Pair<UUID, String> pairUsuario = construirPairUsuario(mensagem.getDestinatario());
            if(!usuariosNaoVistos.containsKey(pairUsuario)) {
                usuariosNaoVistos.put(pairUsuario, 0);
            }
        }

        return usuariosNaoVistos;
    }

    private HistoricoConversa construirHistoricoConversa(String nome, int naoVisto, UUID idContato) {
        return new HistoricoConversa(nome, naoVisto, idContato);
    }

    private List<HistoricoConversa> construirListaHistoricoConversa(List<Mensagem> enviadas, List<Mensagem> recebidas) {
        Map<Pair<UUID, String>, Integer> usuariosNaoVistos = extrairUsuariosConversa(enviadas, recebidas);
        List<HistoricoConversa> historicoConversas = new ArrayList<>();
        usuariosNaoVistos.forEach((key, value) -> {
            HistoricoConversa novoHistorico = construirHistoricoConversa(key.getSecond(), value, key.getFirst());
            historicoConversas.add(novoHistorico);
        });
        return historicoConversas;
    }

    @Override
    public List<HistoricoConversa> buscarHistoricoConversas(UUID idUsuario) {
        if(idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        List<Mensagem> mensagensEnviadas = chatRepository.findByRemetente(usuarioService.buscarUsuario(idUsuario));
        List<Mensagem> mensagensRecebidas = chatRepository.findByDestinatario(usuarioService.buscarUsuario(idUsuario));
        List<HistoricoConversa> historico = construirListaHistoricoConversa(mensagensEnviadas, mensagensRecebidas);
        return historico;
    }
}
