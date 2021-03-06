package br.ufmg.es.tp1.findhelpbackend.repositories.chat;

import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import br.ufmg.es.tp1.findhelpbackend.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IChatRepository extends CrudRepository<Mensagem, UUID> {
    List<Mensagem> findByDestinatarioAndRemetente(Usuario destinatario, Usuario remetente);
    List<Mensagem> findByRemetente(Usuario remetente);
    List<Mensagem> findByDestinatario(Usuario destinatario);
    List<Mensagem> findAllByVistaAndDestinatarioAndRemetente(boolean vista, Usuario destinatario, Usuario remetente);
}
