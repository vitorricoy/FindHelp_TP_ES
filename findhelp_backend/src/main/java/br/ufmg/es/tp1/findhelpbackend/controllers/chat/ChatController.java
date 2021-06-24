package br.ufmg.es.tp1.findhelpbackend.controllers.chat;

import br.ufmg.es.tp1.findhelpbackend.models.*;
import br.ufmg.es.tp1.findhelpbackend.services.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/chat")
public class ChatController {

    private final IChatService chatService;

    @Autowired
    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    List<Mensagem> buscarMensagensConversa(@RequestParam("idUsuario") UUID idUsuario, @RequestParam("idContato") UUID idContato) {
        return chatService.buscarMensagensConversa(idUsuario, idContato);
    }

    @PostMapping
    UUID salvarMensagemConversa(@RequestBody RequisicaoSalvarMensagemConversa requisicao) {
        return chatService.salvarMensagemConversa(requisicao.getIdRemetente(), requisicao.getIdDestinatario(), requisicao.getConteudo());
    }

    @PostMapping(value="/vista")
    boolean marcarMensagensVistas(@RequestBody RequisicaoMarcarMensagensVistas requisicao) {
        return chatService.marcarMensagensVistas(requisicao.getIdUsuario(), requisicao.getIdContato());
    }

    @GetMapping(value = "/historico")
    List<HistoricoConversa> buscarHistoricoConversas(@RequestParam("idUsuario") UUID idUsuario) {
        return chatService.buscarHistoricoConversas(idUsuario);
    }


}
