package com.saper.sistemadeconsultas.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class ConsultaRequestDTO {

    public LocalDate data_consulta;
    public LocalTime hora_consulta;
    public boolean retorno_consulta;
    public String nome_medico;
    public String nome_funcionario;
    public String nome_paciente;


    public LocalDate getData_consulta() {
        return data_consulta;
    }

    public LocalTime getHora_consulta() {
        return hora_consulta;
    }

}
