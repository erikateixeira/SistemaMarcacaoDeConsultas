package com.saper.sistemadeconsultas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class ConsultaRequestDTO {

    public LocalDate data_consulta;
    public LocalTime hora_consulta;

    @NotNull(message = "O campo deve ser preenchido")
    public boolean retorno_consulta;

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome_medico;

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome_funcionario;

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome_paciente;


    public LocalDate getData_consulta() {
        return data_consulta;
    }

    public LocalTime getHora_consulta() {
        return hora_consulta;
    }

}
