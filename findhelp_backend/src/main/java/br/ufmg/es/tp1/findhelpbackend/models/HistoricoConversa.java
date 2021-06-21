package br.ufmg.es.tp1.findhelpbackend.models;

import java.util.UUID;

public class HistoricoConversa {
    private String nomeContato;
    private int naoVisto;
    private UUID idContato;

    public HistoricoConversa() {
    }

    public HistoricoConversa(String nomeContato, int naoVisto, UUID idContato) {
        this.nomeContato = nomeContato;
        this.naoVisto = naoVisto;
        this.idContato = idContato;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public int getNaoVisto() {
        return naoVisto;
    }

    public void setNaoVisto(int naoVisto) {
        this.naoVisto = naoVisto;
    }

    public UUID getIdContato() {
        return idContato;
    }

    public void setIdContato(UUID idContato) {
        this.idContato = idContato;
    }
}
