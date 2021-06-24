package br.ufmg.es.tp1.findhelpbackend.controllers.usuario;

import br.ufmg.es.tp1.findhelpbackend.models.HistoricoConversa;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.ILoginService;
import br.ufmg.es.tp1.findhelpbackend.models.RequisicaoSalvarDadosUsuario;
import br.ufmg.es.tp1.findhelpbackend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
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
    UUID salvarDadosUsuario(@RequestBody RequisicaoSalvarDadosUsuario requisicao) {
        return usuarioService.salvarDadosUsuario(requisicao.getNome(), requisicao
                .getNomeUsuario(), requisicao.getSenha(), requisicao.isPsicologo());
    }

    @GetMapping(value = "/login")
    UUID logarUsuario(@RequestParam("nomeUsuario") String nomeUsuario,@RequestParam("senha") String senha) {
        return loginService.logarUsuario(nomeUsuario, senha);
    }

    @GetMapping(value = "/ativos")
    List<HistoricoConversa> buscarPsicologosOnline() {
        return loginService.buscarPsicologosOnline();
    }
}
