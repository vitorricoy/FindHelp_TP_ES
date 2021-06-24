package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import br.ufmg.es.tp1.findhelpbackend.exceptions.LoginInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.HistoricoConversa;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import br.ufmg.es.tp1.findhelpbackend.repositories.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService implements ILoginService {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioService usuarioService;

    @Autowired
    public LoginService(IUsuarioRepository usuarioRepository, IUsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    private boolean senhaUsuarioInvalida(Usuario usuario, String senha) {
        return usuario == null|| !usuario.getSenha().equals(senha);
    }

    private void registarUsuarioComoAtivo(Usuario usuario) {
        usuario.setAtivo(true);
        usuarioRepository.save(usuario);
    }

    public UUID logarUsuario(String nomeUsuario, String senha) {
        if(nomeUsuario == null|| senha == null|| nomeUsuario.isEmpty()|| senha.isEmpty()) {
            throw new ParametroInvalidoException();
        }
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(senhaUsuarioInvalida(usuario, senha)) {
            throw new LoginInvalidoException();
        }
        registarUsuarioComoAtivo(usuario);
        return usuario.getId();
    }

    private List<HistoricoConversa> construirListaPsicologosOnline(List<Usuario> psicologosOnline) {
        List<HistoricoConversa> retorno = new ArrayList<>();
        for(Usuario usuario:psicologosOnline) {
            retorno.add(new HistoricoConversa(usuario.getNome(), 0, usuario.getId()));
        }
        return retorno;
    }

    public List<HistoricoConversa> buscarPsicologosOnline() {
        List<Usuario> psicologosOnline = usuarioRepository.findByAtivoAndPsicologo(true, true);
        return construirListaPsicologosOnline(psicologosOnline);
    }

    private void registarUsuarioComoInativo(Usuario usuario) {
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public boolean deslogarUsuario(UUID IDUsuario) {
        if(IDUsuario == null) {
            throw new ParametroInvalidoException();
        }
        Usuario usuario = usuarioService.buscarUsuario(IDUsuario);
        registarUsuarioComoInativo(usuario);
        return true;
    }


}
