package br.ufmg.es.tp1.findhelpbackend.models;

import java.util.UUID;

public class RequisicaoMarcarMensagensVistas {
    private UUID idUsuario;
    private UUID idContato;

    public RequisicaoMarcarMensagensVistas(UUID idUsuario, UUID idContato) {
        this.idUsuario = idUsuario;
        this.idContato = idContato;
    }

    public RequisicaoMarcarMensagensVistas() {
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UUID getIdContato() {
        return idContato;
    }

    public void setIdContato(UUID idContato) {
        this.idContato = idContato;
    }
}
