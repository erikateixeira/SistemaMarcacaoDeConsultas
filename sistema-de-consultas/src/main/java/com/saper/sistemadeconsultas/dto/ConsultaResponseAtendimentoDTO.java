package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


public class ConsultaResponseAtendimentoDTO {

    public Long id_consulta;
    public String nome_medico;
    public String especialidade;
    public String sala;
    public LocalDate data_consulta;
    public Time hora_consulta;
    public boolean retorno_consulta;
    public String nome_paciente;
    public String plano_saude;
    public Long num_plano;
    public LocalDate validade_plano;
    public Long valor_consulta;
    public String nome_funcionario;


    public ConsultaResponseAtendimentoDTO(Consulta consulta){
        this.id_consulta = consulta.getId();
        this.nome_medico = consulta.getMedico().getNome();
        this.especialidade = consulta.getMedico().getEspecialidade();
        this.sala = consulta.getMedico().getSala();
        this.data_consulta = consulta.getData();
        this.hora_consulta = consulta.getHora_consulta();
        this.retorno_consulta = consulta.isRetorno_consulta();
        this.nome_paciente = consulta.getPaciente().getNome();
        this.plano_saude = consulta.getPaciente().getPlano_saude();
        this.num_plano = consulta.getPaciente().getNum_plano();
        this.validade_plano = consulta.getPaciente().getValidade_plano();
        this.valor_consulta = consulta.getMedico().getValor_consulta();
        this.nome_funcionario = consulta.getFuncionario().getNome();

    }




}
