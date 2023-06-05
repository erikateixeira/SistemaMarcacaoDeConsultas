package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Medico;

import java.util.Date;
import java.util.List;

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
    public List<String> data_disponivel;
    public Date hora_inicial;
    public Date hora_final;
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
        this.data_disponivel = medico.getData_disponivel();
        this.hora_inicial = medico.getHora_inicial();
        this.hora_final = medico.getHora_final();
        this.valor_consulta = medico.getValor_consulta();
    }

}
