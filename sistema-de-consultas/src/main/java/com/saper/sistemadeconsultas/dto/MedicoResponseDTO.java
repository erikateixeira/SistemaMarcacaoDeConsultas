package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.DiaSemana;
import com.saper.sistemadeconsultas.model.Medico;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MedicoResponseDTO {

    public Long id;
    public String nome;
    public String cnpj;
    public String crm_estado;
    public String crm_num;
    public String telefone;
    public String email;
    public String especialidade;
    public String sala;
    public String login;
    public List<String> diasDisponiveis;
    public LocalTime hora_inicial;
    public LocalTime hora_final;
    public Long valor_consulta;

    public List<String> convertDiasSemana(List<DiaSemana> diasSemanaEnum) {
        List<String> diasDisponiveis = new ArrayList<>();
        for (DiaSemana diaSemana : diasSemanaEnum) {
            diasDisponiveis.add(diaSemana.toString());
        }
        return diasDisponiveis;
    }

    public MedicoResponseDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.cnpj = medico.getCnpj();
        this.crm_estado = medico.getCrm_estado();
        this.crm_num = medico.getCrm_num();
        this.telefone = medico.getTelefone();
        this.email = medico.getEmail();
        this.especialidade = medico.getEspecialidade();
        this.sala = medico.getSala();
        this.login = medico.getLogin();
        this.diasDisponiveis = convertDiasSemana(medico.getDiasDisponiveis());

        LocalDateTime hora_inicial = medico.getHora_inicial();
        LocalTime hora_inicial_sozinha = hora_inicial.toLocalTime();
        this.hora_inicial= hora_inicial_sozinha;

        LocalDateTime hora_final = medico.getHora_final();
        LocalTime hora_final_sozinha = hora_final.toLocalTime();
        this.hora_final= hora_final_sozinha;

        this.valor_consulta = medico.getValor_consulta();
    }

}
