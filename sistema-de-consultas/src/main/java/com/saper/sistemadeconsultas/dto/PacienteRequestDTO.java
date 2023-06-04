package com.saper.sistemadeconsultas.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class PacienteRequestDTO {

    public String nome_id;
    public String nome;
    public String cpf;
    public String passaporte;
    public Date data_nascimento;
    public String nome_responsavel;
    public String cpf_responsavel;
    public String genero;
    public String endereco;
    public String cep;
    public String bairro;
    public String cidade;
    public String estado;
    public String telefone;
    public String email;
    public String plano_saude;
    public Long num_plano;
    public Date validade_plano;


}
