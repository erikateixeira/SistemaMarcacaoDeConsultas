package com.saper.sistemadeconsultas.dto;

import java.time.LocalTime;
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
    public List<String> diasDisponiveis;
    public LocalTime hora_inicial;
    public LocalTime hora_final;
    public Long valor_consulta;

    public List<String> getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public LocalTime getHora_inicial() {
        return hora_inicial;
    }

    public LocalTime getHora_final() {
        return hora_final;
    }

}
