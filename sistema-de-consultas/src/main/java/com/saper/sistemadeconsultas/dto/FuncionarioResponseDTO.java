package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Funcionario;

public class FuncionarioResponseDTO {
    public Long id;
    public String nome;
    public String cpf;
    public String rg;
    public String data_nascimento;
    public String endereco;
    public String cep;
    public String bairro;
    public String cidade;
    public String estado;
    public String telefone;
    public String email;
    public String funcao;
    public String login;

    public FuncionarioResponseDTO(Funcionario funcionario){
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.rg = funcionario.getRg();
        this.data_nascimento = funcionario.getData_nascimento();
        this.endereco = funcionario.getEndereco();
        this.cep = funcionario.getCep();
        this.bairro = funcionario.getBairro();
        this.cidade = funcionario.getCidade();
        this.estado = funcionario.getEstado();
        this.telefone = funcionario.getTelefone();
        this.email = funcionario.getEmail();
        this.funcao = funcionario.getFuncao();
        this.login = funcionario.getLogin();
    }


}
