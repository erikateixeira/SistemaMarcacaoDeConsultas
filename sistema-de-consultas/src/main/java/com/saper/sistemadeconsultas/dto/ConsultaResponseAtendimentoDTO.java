package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class ConsultaResponseAtendimentoDTO {

    public Long id_consulta;
    public String nome_medico;
    public String especialidade;
    public String sala;
    public LocalDate data_consulta;
    public LocalTime hora_consulta;
    public boolean retorno_consulta;
    public String nome_paciente;
    public String plano_saude;
    public Long num_plano;
    public LocalDate validade_plano;
    public Long valor_consulta;
    public String nome_funcionario;


    public ConsultaResponseAtendimentoDTO(Consulta consulta){
        this.id_consulta = consulta.getId();

        Medico medico = consulta.getMedico();
        if (medico != null) {
            this.nome_medico = consulta.getMedico().getNome();
            this.especialidade = consulta.getMedico().getEspecialidade();
            this.sala = consulta.getMedico().getSala();
            this.valor_consulta = consulta.getMedico().getValor_consulta();
        } else {
            this.nome_medico = "null";
            this.especialidade = "null";
            this.sala = "null";
            this.valor_consulta = null;
        }

        this.data_consulta = consulta.getData();

        LocalDateTime hora_consulta = consulta.getHora_consulta();
        LocalTime hora_consulta_sozinha = hora_consulta.toLocalTime();
        this.hora_consulta = hora_consulta_sozinha;

        this.retorno_consulta = consulta.isRetorno_consulta();

        Paciente paciente = consulta.getPaciente();
        if (paciente != null) {
            this.nome_paciente = consulta.getPaciente().getNome();
            this.plano_saude = consulta.getPaciente().getPlano_saude();
            this.num_plano = consulta.getPaciente().getNum_plano();
            this.validade_plano = consulta.getPaciente().getValidade_plano();
        } else {
            this.nome_paciente = "null";
            this.plano_saude = "null";
            this.num_plano = null;
            this.validade_plano = null;
        }

        Funcionario funcionario = consulta.getFuncionario();
        if (funcionario != null) {
            this.nome_funcionario = consulta.getFuncionario().getNome();
        } else {
            this.nome_funcionario = "null";
        }

    }




}