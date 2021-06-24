package br.ufmg.es.tp1.findhelpbackend.models;

public class RequisicaoSalvarDadosUsuario {
    private String nome;
    private String nomeUsuario;
    private String senha;
    private boolean psicologo;

    public RequisicaoSalvarDadosUsuario(String nome, String nomeUsuario, String senha, boolean psicologo) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.psicologo = psicologo;
    }

    public RequisicaoSalvarDadosUsuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isPsicologo() {
        return psicologo;
    }

    public void setPsicologo(boolean psicologo) {
        this.psicologo = psicologo;
    }
}
