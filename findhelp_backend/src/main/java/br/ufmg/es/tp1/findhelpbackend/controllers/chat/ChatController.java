package br.ufmg.es.tp1.findhelpbackend.controllers.chat;

import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.services.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    List<Mensagem> buscarMensagensConversa(UUID idRecipiente, UUID idRemetente) {
        return chatService.buscarMensagensConversa(idRecipiente, idRemetente);
    }

}
