package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultaResponseMedicoDTO {
    public LocalTime hora_consulta;
    public String nome_paciente;

    public ConsultaResponseMedicoDTO(Consulta consulta){
        Paciente paciente = consulta.getPaciente();
        if (paciente != null) {
            this.nome_paciente = consulta.getPaciente().getNome();
        } else {
            this.nome_paciente = "null";
        }

        LocalDateTime hora_consulta = consulta.getHora_consulta();
        LocalTime hora_consulta_sozinha = hora_consulta.toLocalTime();
        this.hora_consulta = hora_consulta_sozinha;

    }


}
