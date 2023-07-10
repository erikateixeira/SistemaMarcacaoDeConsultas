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

    @NotBlank
    public String data_consulta;

    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", message = "Formato de horário inválido. Use o formato HH:MM:SS")
    public String hora_consulta;

    @NotNull(message = "O campo deve ser preenchido com 0 (consulta padrão) ou 1 (retorno de consulta.")
    public boolean retorno_consulta;

    @NotBlank
    @Pattern(regexp = "(?i)cardiologista|dermatologista|ginecologista", message = "Especialidade deve ser CARDIOLOGISTA, DERMATOLOGISTA OU GINECOLOGISTA.")
    public String especialidade;

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome_medico;

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome_funcionario;

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome_paciente;

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(String hora_consulta) {
        this.hora_consulta = hora_consulta;
    }

    public boolean isRetorno_consulta() {
        return retorno_consulta;
    }

    public void setRetorno_consulta(boolean retorno_consulta) {
        this.retorno_consulta = retorno_consulta;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNome_medico() {
        return nome_medico;
    }

    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }
}
