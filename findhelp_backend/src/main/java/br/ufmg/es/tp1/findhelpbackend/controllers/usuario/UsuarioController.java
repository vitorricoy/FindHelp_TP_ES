package br.ufmg.es.tp1.findhelpbackend.controllers.usuario;

import br.ufmg.es.tp1.findhelpbackend.models.RequisicaoSalvarDadosUsuario;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/usuario")
public class UsuarioController {
    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    UUID salvarDadosUsuario(@RequestBody RequisicaoSalvarDadosUsuario requisicao) {
        return usuarioService.salvarDadosUsuario(requisicao.getNome(), requisicao
                .getNomeUsuario(), requisicao.getSenha(), requisicao.isPsicologo());
    }
}
