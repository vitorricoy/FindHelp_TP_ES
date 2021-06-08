package br.ufmg.es.tp1.findhelpbackend.controllers.chat;

import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.services.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private final IChatService chatService;

    @Autowired
    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    List<Mensagem> buscarMensagensConversa(UUID idUsuario, UUID idContato) {
        return chatService.buscarMensagensConversa(idUsuario, idContato);
    }

    @PostMapping
    UUID salvarMensagemConversa(UUID idUsuario, UUID idContato, String conteudo) {
        return chatService.salvarMensagemConversa(idUsuario, idContato, conteudo);
    }

}
