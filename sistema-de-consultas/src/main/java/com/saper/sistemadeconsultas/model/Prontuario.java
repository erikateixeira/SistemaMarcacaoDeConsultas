package com.saper.sistemadeconsultas.model;

import jakarta.persistence.*;

@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario")
    private Long id;

    @Lob
    @Column(name = "arquivo_prontuario")
    private byte[] pdf;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    Consulta consulta;

    public Prontuario() {
    }

    public Prontuario(Long id, byte[] pdf, Consulta consulta) {
        this.id = id;
        this.pdf = pdf;
        this.consulta = consulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
