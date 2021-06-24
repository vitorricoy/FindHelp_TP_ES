package br.ufmg.es.tp1.findhelpbackend.models;

import java.util.UUID;

public class RequisicaoSalvarMensagemConversa {
    private UUID idRemetente;
    private UUID idDestinatario;
    private String conteudo;

    public RequisicaoSalvarMensagemConversa(UUID idRemetente, UUID idDestinatario, String conteudo) {
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.conteudo = conteudo;
    }

    public RequisicaoSalvarMensagemConversa() {
    }

    public UUID getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(UUID idRemetente) {
        this.idRemetente = idRemetente;
    }

    public UUID getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(UUID idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
