package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


public class ConsultaResponseDTO {

    public Long id_consulta;
    public LocalDate data_consulta;
    public Time hora_consulta;
    public boolean retorno_consulta;
    public String nome_medico;
    public String nome_paciente;
    public String nome_funcionario;

    public ConsultaResponseDTO(Consulta consulta){
        this.id_consulta = consulta.getId();
        this.data_consulta = consulta.getData();
        this.hora_consulta = consulta.getHora_consulta();
        this.retorno_consulta = consulta.isRetorno_consulta();
        this.nome_medico = consulta.getMedico().getNome();
        this.nome_paciente = consulta.getPaciente().getNome();
        this.nome_funcionario = consulta.getFuncionario().getNome();

    }



}
