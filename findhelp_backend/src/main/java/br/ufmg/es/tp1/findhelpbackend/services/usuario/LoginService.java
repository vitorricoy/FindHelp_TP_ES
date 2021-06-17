package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import br.ufmg.es.tp1.findhelpbackend.exceptions.LoginInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import br.ufmg.es.tp1.findhelpbackend.repositories.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class LoginService implements ILoginService {
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public LoginService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private boolean senhaUsuarioInvalida(Usuario usuario, String senha) {
        return usuario == null|| !usuario.getSenha().equals(senha);
    }

    public UUID logarUsuario(String nomeUsuario, String senha) {
        if(nomeUsuario == null|| senha == null|| nomeUsuario.isEmpty()|| senha.isEmpty()) {
            throw new ParametroInvalidoException();
        }
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(senhaUsuarioInvalida(usuario, senha)) {
            throw new LoginInvalidoException();
        }
        return usuario.getId();
    }
}
