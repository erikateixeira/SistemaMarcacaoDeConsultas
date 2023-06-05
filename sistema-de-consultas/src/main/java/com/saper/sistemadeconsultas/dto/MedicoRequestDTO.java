package com.saper.sistemadeconsultas.dto;

import java.util.Date;
import java.util.List;

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
    public List<String> data_disponivel;
    public Date hora_inicial;
    public Date hora_final;
    public Long valor_consulta;

}
