package com.saper.sistemadeconsultas.model;

import jakarta.persistence.*;

@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario")
    private Long id;

    @Column(name = "nome_arquivo")
    private String nome;

    @Column(name = "extensão_arquivo")
    private String extensao;

    @Column(name = "tamanho_arquivo")
    private Long tamanho;

    @Lob
    @Column(name = "arquivo_prontuario")
    private byte[] pdf;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    Consulta consulta;

    public Prontuario() {
    }

    public Prontuario(Long id, String nome, String extensao, Long tamanho, byte[] pdf, Consulta consulta) {
        this.id = id;
        this.nome = nome;
        this.extensao = extensao;
        this.tamanho = tamanho;
        this.pdf = pdf;
        this.consulta = consulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
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
