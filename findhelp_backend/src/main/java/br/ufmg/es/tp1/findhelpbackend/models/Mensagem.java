package br.ufmg.es.tp1.findhelpbackend.models;

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
    @JoinColumn(name = "destinatario")

    private Usuario destinatario;

    private Boolean vista;

    private LocalDateTime horarioEnvio;

    public Mensagem() { }

    public Mensagem(UUID id, String conteudo, Usuario remetente, Usuario destinatario, boolean vista, LocalDateTime horarioEnvio) {
        this.id = id;
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.vista = vista;
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

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario recipiente) {
        this.destinatario = recipiente;
    }

    public Boolean isVista() {
        return vista;
    }

    public void setVista(Boolean vista) {
        this.vista = vista;
    }

    public LocalDateTime getHorarioEnvio() {
        return horarioEnvio;
    }

    public void setHorarioEnvio(LocalDateTime horarioEnvio) {
        this.horarioEnvio = horarioEnvio;
    }
}
