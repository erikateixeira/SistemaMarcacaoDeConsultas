package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.enums.DiaSemana;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.List;


public class MedicoRequestDTO {

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome;

    @NotBlank
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "Formato de CNPJ deve ser XX.XXX.XXX/YYYY-ZZ")
    public String cnpj;

    @NotBlank
    @Size(min = 2, max = 2, message = "CRM-ESTADO deve conter 2 caracteres")
    public String crm_estado;

    @NotBlank
    @Size(min = 6, max = 6, message = "CRM-NÚMERO deve conter 6 dígitos")
    public String crm_num;

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\) 9 \\d{4}-\\d{4}", message = "Telefone deve ter o formato (XX) 9 XXXX-XXXX")
    public String telefone;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(max = 30)
    /*@Pattern.List({
        @Pattern(regexp = "(?i)cardiologista"),
        @Pattern(regexp = "(?i)dermatologista"),
        @Pattern(regexp = "(?i)ginecologista")
    })*/
    public String especialidade;

    @NotBlank
    @Size(min = 2, max = 2, message = "Sala deve conter 2 dígitos")
    public String sala;

    @NotBlank
    @Size(max = 30)
    public String login;

    @NotBlank
    @Size(max = 15)
    public String senha;

    @NotEmpty(message = "A lista de dias disponíveis não pode estar vazia")
    public List<String> diasDisponiveis;

    @NotBlank
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", message = "Formato de horário inválido. Use o formato HH:MM:SS")
    public String hora_inicial;

    @NotBlank
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", message = "Formato de horário inválido. Use o formato HH:MM:SS")
    public String hora_final;

    @NotNull
    @DecimalMin(value = "100", message = "O valor da consulta deve ser maior ou igual a R$ 100,00")
    @DecimalMax(value = "1000.00", message = "O valor da consulta deve ser menor ou igual a 1000.00")
    public Long valor_consulta;

    public List<String> getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public String getHora_inicial() {
        return hora_inicial;
    }

    public String getHora_final() {
        return hora_final;
    }
}
