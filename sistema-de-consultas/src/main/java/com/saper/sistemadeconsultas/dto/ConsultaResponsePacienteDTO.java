package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultaResponsePacienteDTO {

    public String nome_paciente;
    public String nome_medico;
    public LocalDate data_consulta;
    public LocalTime hora_consulta;
    public boolean retorno_consulta;



    public ConsultaResponsePacienteDTO(Consulta consulta) {
        Paciente paciente = consulta.getPaciente();
        if (paciente != null) {
            this.nome_paciente = consulta.getPaciente().getNome();
        } else {
            this.nome_paciente = "null";
        }

        Medico medico = consulta.getMedico();
        if (medico != null) {
            this.nome_medico = consulta.getMedico().getNome();
        } else {
            this.nome_medico = "null";
        }

        this.data_consulta = consulta.getData();

        LocalDateTime hora_consulta = consulta.getHora_consulta();
        LocalTime hora_consulta_sozinha = hora_consulta.toLocalTime();
        this.hora_consulta = hora_consulta_sozinha;

        this.retorno_consulta = consulta.isRetorno_consulta();





    }



}
