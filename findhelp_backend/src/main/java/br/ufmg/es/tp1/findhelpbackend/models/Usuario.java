package br.ufmg.es.tp1.findhelpbackend.models;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class Usuario {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    private String nome;

    private String nomeUsuario;

    private String senha;

    private boolean psicologo;

    private boolean ativo;

    public Usuario(UUID id, String nome, String nomeUsuario, String senha, boolean psicologo, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.psicologo = psicologo;
        this.ativo = ativo;
    }

    public Usuario() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
