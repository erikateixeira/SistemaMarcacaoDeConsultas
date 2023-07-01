package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Funcionario;

public class FuncionarioResponseNomeDTO {

    public String nome;

    public FuncionarioResponseNomeDTO(Funcionario funcionario){
        this.nome = funcionario.getNome();
    }
}
