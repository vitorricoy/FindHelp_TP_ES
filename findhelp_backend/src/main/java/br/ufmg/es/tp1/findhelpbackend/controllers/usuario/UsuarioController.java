package br.ufmg.es.tp1.findhelpbackend.controllers.usuario;

import br.ufmg.es.tp1.findhelpbackend.services.usuario.ILoginService;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    private final IUsuarioService usuarioService;

    private final ILoginService loginService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, ILoginService loginService) {
        this.usuarioService = usuarioService;
        this.loginService = loginService;
    }

    @PostMapping
    UUID salvarDadosUsuario(String nome, String nomeUsuario, String senha, boolean psicologo) {
        return usuarioService.salvarDadosUsuario(nome, nomeUsuario, senha, psicologo);
    }

    @GetMapping(value = "/login")
    UUID logarUsuario(String nomeUsuario, String senha) {
        return loginService.logarUsuario(nomeUsuario, senha);
    }
}
