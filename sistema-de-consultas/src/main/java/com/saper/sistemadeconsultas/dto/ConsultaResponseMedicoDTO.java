package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConsultaResponseMedicoDTO {
    public LocalTime hora_consulta;
    public String data_consulta;
    public String nome_paciente;

    public ConsultaResponseMedicoDTO(Consulta consulta){
        Paciente paciente = consulta.getPaciente();
        if (paciente != null) {
            this.nome_paciente = consulta.getPaciente().getNome();
        } else {
            this.nome_paciente = "null";
        }

        LocalDate dataConsulta = consulta.getData();
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_consulta = dataConsulta.format(formatoSaida);

        LocalDateTime hora_consulta = consulta.getHora();
        LocalTime hora_consulta_sozinha = hora_consulta.toLocalTime();
        this.hora_consulta = hora_consulta_sozinha;

    }


}
