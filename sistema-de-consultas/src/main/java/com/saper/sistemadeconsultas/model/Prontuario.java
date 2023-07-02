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

    @Column(name = "caminho_arquivo")
    private String caminho;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    Consulta consulta;

    public Prontuario() {
    }

    public Prontuario(Long id, String nome, String caminho, Consulta consulta) {
        this.id = id;
        this.nome = nome;
        this.caminho = caminho;
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

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}