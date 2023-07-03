package com.saper.sistemadeconsultas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class ConsultaRequestDTO {

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data deve ser dd/mm/yyyy")
    public String data_consulta;

    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", message = "Formato de horário inválido. Use o formato HH:MM:SS")
    public String hora_consulta;

    @NotNull(message = "O campo deve ser preenchido com 0 (consulta padrão) ou 1 (retorno de consulta.")
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


}
