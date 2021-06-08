package br.ufmg.es.tp1.findhelpbackend.models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
public class Mensagem {

    @Id
    private UUID id;

    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "remetente")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "recipiente")

    private Usuario recipiente;

    private LocalDateTime horarioEnvio;

    public Mensagem() { }

    public Mensagem(UUID id, String conteudo, Usuario remetente, Usuario recipiente, LocalDateTime horarioEnvio) {
        this.id = id;
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.recipiente = recipiente;
        this.horarioEnvio = horarioEnvio;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(Usuario recipiente) {
        this.recipiente = recipiente;
    }

    public LocalDateTime getHorarioEnvio() {
        return horarioEnvio;
    }

    public void setHorarioEnvio(LocalDateTime horarioEnvio) {
        this.horarioEnvio = horarioEnvio;
    }
}
