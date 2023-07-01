package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Funcionario;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
    public String senha;

    public FuncionarioResponseDTO(Funcionario funcionario){
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.rg = funcionario.getRg();

        LocalDate dataNascimento = funcionario.getData_nascimento();
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_nascimento = dataNascimento.format(formatoSaida);

        this.endereco = funcionario.getEndereco();
        this.cep = funcionario.getCep();
        this.bairro = funcionario.getBairro();
        this.cidade = funcionario.getCidade();
        this.estado = funcionario.getEstado();
        this.telefone = funcionario.getTelefone();
        this.email = funcionario.getEmail();
        this.funcao = funcionario.getFuncao();
        this.login = funcionario.getLogin();

        if(funcionario.getSenha()!=null) {
            this.senha = mascararSenha(funcionario.getSenha());
        }
    }

    public String mascararSenha(String senha) {
        int tamanhoSenha = senha.length();

        String senhaMascarada = senha.replaceAll(".", "*");

        return senhaMascarada;
    }


}
