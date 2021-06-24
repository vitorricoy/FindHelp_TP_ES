package br.ufmg.es.tp1.findhelpbackend.models;

import java.util.UUID;

public class RequisicaoDeslogar {

    private UUID idUsuario;

    public RequisicaoDeslogar(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RequisicaoDeslogar() {
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
