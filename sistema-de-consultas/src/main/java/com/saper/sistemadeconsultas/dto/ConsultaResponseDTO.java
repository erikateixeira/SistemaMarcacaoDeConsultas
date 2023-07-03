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
import java.time.format.DateTimeFormatter;


public class ConsultaResponseDTO {

    public Long id_consulta;
    public String data_consulta;
    public LocalTime hora_consulta;
    public boolean retorno_consulta;
    public String nome_medico;
    public String nome_paciente;
    public String nome_funcionario;

    public ConsultaResponseDTO(Consulta consulta){
        this.id_consulta = consulta.getId();

        LocalDate dataConsulta = consulta.getData();
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_consulta = dataConsulta.format(formatoSaida);

        LocalDateTime hora_consulta = consulta.getHora_consulta();
        LocalTime hora_consulta_sozinha = hora_consulta.toLocalTime();
        this.hora_consulta = hora_consulta_sozinha;

        this.retorno_consulta = consulta.isRetorno_consulta();

        Medico medico = consulta.getMedico();
        if (medico != null) {
            this.nome_medico = consulta.getMedico().getNome();
        } else {
            this.nome_medico = "null";
        }

        Paciente paciente = consulta.getPaciente();
        if (paciente != null) {
            this.nome_paciente = consulta.getPaciente().getNome();
        } else {
            this.nome_paciente = "null";
        }

        Funcionario funcionario = consulta.getFuncionario();
        if (funcionario != null) {
            this.nome_funcionario = consulta.getFuncionario().getNome();
        } else {
            this.nome_funcionario = "null";
        }


    }



}
