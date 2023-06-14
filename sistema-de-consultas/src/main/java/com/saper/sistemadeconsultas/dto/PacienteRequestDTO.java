package com.saper.sistemadeconsultas.dto;


import java.sql.Date;
import java.time.LocalDate;

public class PacienteRequestDTO {

    public String nome_id;
    public String nome;
    public String cpf;
    public String passaporte;
    public LocalDate data_nascimento;
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
    public LocalDate validade_plano;


}
