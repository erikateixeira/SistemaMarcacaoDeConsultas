package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConsultaResponseIniciarDTO {

    public String data_consulta;
    public LocalTime hora_consulta;
    public String nome_medico;
    public String crm_estado;
    public String crm_num;
    public String especialidade;
    public String nome_paciente;
    public String cpf;
    public String passaporte;
    public String data_nascimento;
    public String telefone;
    public String email;

    public ConsultaResponseIniciarDTO(Consulta consulta){

        LocalDate dataConsulta = consulta.getData();
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_consulta = dataConsulta.format(formatoSaida);

        LocalDateTime hora_consulta = consulta.getHora();
        LocalTime hora_consulta_sozinha = hora_consulta.toLocalTime();
        this.hora_consulta = hora_consulta_sozinha;

        this.nome_medico = consulta.getMedico().getNome();
        this.crm_estado = consulta.getMedico().getCrm_estado();
        this.crm_num =consulta.getMedico().getCrm_num();
        this.especialidade = consulta.getMedico().getEspecialidade();

        this.nome_paciente = consulta.getPaciente().getNome();
        this.cpf = consulta.getPaciente().getCpf();
        this.passaporte = consulta.getPaciente().getPassaporte();

        LocalDate dataNascimento = consulta.getPaciente().getData_nascimento();
        this.data_nascimento = dataNascimento.format(formatoSaida);

        this.telefone = consulta.getPaciente().getTelefone();
        this.email = consulta.getPaciente().getEmail();



    }
}
