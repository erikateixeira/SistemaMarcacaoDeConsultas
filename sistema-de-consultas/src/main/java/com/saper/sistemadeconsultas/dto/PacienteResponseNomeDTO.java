package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Paciente;

public class PacienteResponseNomeDTO {

    public String nome;

    public PacienteResponseNomeDTO(Paciente paciente) {
        this.nome = paciente.getNome();
    }

}
