package br.ufmg.es.tp1.findhelpbackend.models;

public class HistoricoConversa {
    private String nomeContato;
    private int naoVisto;

    public HistoricoConversa() {
    }

    public HistoricoConversa(String nomeContato, int naoVisto) {
        this.nomeContato = nomeContato;
        this.naoVisto = naoVisto;
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
}
