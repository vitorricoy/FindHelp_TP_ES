package br.ufmg.es.tp1.findhelpbackend.repositories.usuario;

import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUsuarioRepository extends CrudRepository<Usuario, UUID> {
    Usuario findByNomeUsuario(String nomeUsuario);
}
