package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Medico;

public class MedicoResponseNomeDTO {

    public String nome;

    public MedicoResponseNomeDTO(Medico medico) {
        this.nome = medico.getNome();
    }
}
