package com.saper.sistemadeconsultas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Set;

@Entity
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "nome_funcionario",
            nullable = false,
            unique = true,
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_nascimento;

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
            length = 16) // (85) 9 9623-0391
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
            length = 30)
    private String login;

    @Column(name = "senha_funcionario",
            length = 80) //tamanho para bcrypt
    private String senha;

    @OneToMany(mappedBy = "funcionario", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Set<Consulta> consultas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "funcionario_role",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    Set<Role> roles;

    public Funcionario() {
    }

    public Funcionario(FuncionarioResquestDTO funcionarioResquestDTO){
        this.nome = funcionarioResquestDTO.nome;
        this.cpf = funcionarioResquestDTO.cpf;
        this.rg = funcionarioResquestDTO.rg;
        this.data_nascimento = LocalDate.parse(funcionarioResquestDTO.data_nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.endereco = funcionarioResquestDTO.endereco;
        this.cep = funcionarioResquestDTO.cep;
        this.bairro = funcionarioResquestDTO.bairro;
        this.cidade = funcionarioResquestDTO.cidade;
        this.estado = funcionarioResquestDTO.estado;
        this.telefone = funcionarioResquestDTO.telefone;
        this.email = funcionarioResquestDTO.email;
        this.funcao = funcionarioResquestDTO.funcao;
        this.login = funcionarioResquestDTO.login;

        if(funcionarioResquestDTO.senha!=null) {
            this.senha = new BCryptPasswordEncoder().encode(funcionarioResquestDTO.senha);
        }
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Funcionario(Long id, String nome, String cpf, String rg, LocalDate data_nascimento, String endereco, String cep, String bairro, String cidade, String estado, String telefone, String email, String funcao, String login, String senha) {
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

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
