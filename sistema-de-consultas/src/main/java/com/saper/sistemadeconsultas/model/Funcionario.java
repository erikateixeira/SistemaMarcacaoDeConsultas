package com.saper.sistemadeconsultas.model;

import java.util.*;

import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import jakarta.persistence.*;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "nome_funcionario",
            nullable = false,
            length = 80)
    private String nome;

    @Column(name = "cpf_funcionario",
            nullable = false,
            unique = true,
            length = 14)
    private String cpf; //xxx.xxx.xxx-xx

    @Column(name = "rg_funcionario",
            nullable = false,
            unique = true,
            length = 20)
    private String rg;

    @Column(name = "data_nascimento_funcionario",
            nullable = false)
    private Date data_nascimento;

    @Column(name = "endereco_funcionario",
            nullable = false)
    private String endereco;

    @Column(name = "cep_funcionario",
            nullable = false,
            length = 9) //xxxxx-xxx
    private String cep;

    @Column(name = "bairro_funcionario",
            nullable = false,
            length = 80)
    private String bairro;

    @Column(name = "cidade_funcionario",
            nullable = false,
            length = 80)
    private String cidade;

    @Column(name = "estado_funcionario",
            nullable = false,
            length = 2)
    private String estado;

    @Column(name = "telefone_funcionario",
            nullable = false,
            unique = true,
            length = 16) // (85) 9.9623-0391
    private String telefone;

    @Column(name = "email_funcionario",
            nullable = false,
            unique = true,
            length = 80)
    private String email;

    @Column(nullable = false,
            length = 40)
    private String funcao;

    @Column(name = "login_funcionario",
            unique = true,
            length = 45)
    private String login;

    @Column(name = "senha_funcionario",
            length = 15)
    private String senha;

    public Funcionario() {
    }

    public Funcionario(FuncionarioResquestDTO funcionarioResquestDTO){
        this.nome = funcionarioResquestDTO.nome;
        this.cpf = funcionarioResquestDTO.cpf;
        this.rg = funcionarioResquestDTO.rg;
        this.data_nascimento = funcionarioResquestDTO.data_nascimento;
        this.endereco = funcionarioResquestDTO.endereco;
        this.cep = funcionarioResquestDTO.cep;
        this.bairro = funcionarioResquestDTO.bairro;
        this.cidade = funcionarioResquestDTO.cidade;
        this.estado = funcionarioResquestDTO.estado;
        this.telefone = funcionarioResquestDTO.telefone;
        this.email = funcionarioResquestDTO.email;
        this.funcao = funcionarioResquestDTO.funcao;
        this.login = funcionarioResquestDTO.login;
        this.senha = funcionarioResquestDTO.senha;
    }

    public Funcionario(Long id, String nome, String cpf, String rg, Date data_nascimento, String endereco, String cep, String bairro, String cidade, String estado, String telefone, String email, String funcao, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
        this.funcao = funcao;
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
