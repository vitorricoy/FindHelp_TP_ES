package br.ufmg.es.tp1.findhelpbackend.controllers.usuario;

import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    UUID salvarDadosUsuario(String nome, String nomeUsuario, String senha, boolean psicologo) {
        return usuarioService.salvarDadosUsuario(nome, nomeUsuario, senha, psicologo);
    }
}
