package br.ufmg.es.tp1.findhelpbackend.services.usuario;

import br.ufmg.es.tp1.findhelpbackend.exceptions.NaoEncontradoException;
import br.ufmg.es.tp1.findhelpbackend.exceptions.ParametroInvalidoException;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import br.ufmg.es.tp1.findhelpbackend.repositories.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario construirUsuario(String nome, String nomeUsuario, String senha, boolean psicologo) {
        return new Usuario(UUID.randomUUID(), nome, nomeUsuario, senha, psicologo);
    }

    public UUID salvarDadosUsuario(String nome, String nomeUsuario, String senha, boolean psicologo) {
        if(nome == null|| nomeUsuario == null || senha == null || nome.isEmpty() || nomeUsuario.isEmpty() || senha.isEmpty()) {
            throw new ParametroInvalidoException();
        }
        Usuario novoUsuario = construirUsuario(nome, nomeUsuario, senha, psicologo);
        usuarioRepository.save(novoUsuario);
        return novoUsuario.getId();
    }

    public Usuario buscarUsuario(UUID idUsuario) {
        if(idUsuario == null) {
            throw new ParametroInvalidoException();
        }
        return usuarioRepository.findById(idUsuario).orElseThrow(NaoEncontradoException::new);
    }
}