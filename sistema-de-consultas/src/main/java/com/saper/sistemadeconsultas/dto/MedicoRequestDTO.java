package com.saper.sistemadeconsultas.dto;

import java.sql.Time;


public class MedicoRequestDTO {

    public String nome;
    public String cnpj;
    public String crm_estado;
    public String crm_num;
    public String telefone;
    public String email;
    public String especialidade;
    public String sala;
    public String login;
    public String senha;
    public String[] diasDisponiveis;
    public Time hora_inicial;
    public Time hora_final;
    public Long valor_consulta;

}
