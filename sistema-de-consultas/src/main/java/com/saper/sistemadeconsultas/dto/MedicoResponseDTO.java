package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Medico;


import java.sql.Time;

public class MedicoResponseDTO {

    public Long id;
    public String nome;
    public String cnpj;
    public String crm_estado;
    public String crm_num;
    public String telefone;
    public String email;
    public String especialidade;
    public String sala;
    public String login;
    public String[] diasDisponiveis;
    public Time hora_inicial;
    public Time hora_final;
    public Long valor_consulta;

    public MedicoResponseDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.cnpj = medico.getCnpj();
        this.crm_estado = medico.getCrm_estado();
        this.crm_num = medico.getCrm_num();
        this.telefone = medico.getTelefone();
        this.email = medico.getEmail();
        this.especialidade = medico.getEspecialidade();
        this.sala = medico.getSala();
        this.login = medico.getSala();
        this.diasDisponiveis = medico.getDiasDisponiveis();
        this.hora_inicial = medico.getHora_inicial();
        this.hora_final = medico.getHora_final();
        this.valor_consulta = medico.getValor_consulta();
    }

}
