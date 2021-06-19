package br.ufmg.es.tp1.findhelpbackend.repositories.chat;

import br.ufmg.es.tp1.findhelpbackend.models.Mensagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IChatRepository extends CrudRepository<Mensagem, UUID> {
    List<Mensagem> findByDestinatarioAndRemetente(UUID destinatario, UUID remetente);
    List<Mensagem> findByRemetente(UUID remetente);
    List<Mensagem> findByDestinatario(UUID destinatario);
    List<Mensagem> findAllByVistaAndDestinatarioAndRemetente(boolean vista, UUID destinatario, UUID remetente);
}
