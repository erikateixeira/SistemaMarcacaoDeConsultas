package com.saper.sistemadeconsultas.model;

import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long id;

    @Column(name = "nome_medico",
            unique = true,
            nullable = false,
            length = 80)
    private String nome;

    @Column(unique = true,
            nullable = false,
            length = 18) // XX.XXX.XXX/000X-XX
    private String cnpj;

    @Column(nullable = false,
            length = 2) //CRM-CE
    private String crm_estado;

    @Column(nullable = false,
            length = 6) // CRM-CE 123456
    private String crm_num;

    @Column(name = "telefone_medico",
            nullable = false,
            unique = true,
            length = 16)
    private String telefone;

    @Column(name = "email_medico",
            nullable = false,
            unique = true,
            length = 80)
    private String email;

    @Column(nullable = false,
            length = 30)
    private String especialidade;

    @Column(nullable = false,
            length = 2)
    private String sala;

    @Column(name = "login_medico",
            nullable = false,
            unique = true,
            length = 45)
    private String login;

    @Column(name = "senha_medico",
            nullable = false,
            length = 15)
    private String senha;

    @Column(nullable = false)
    private List<String> data_disponivel;

    @Column(nullable = false)
    private Date hora_inicial;

    @Column(nullable = false)
    private Date hora_final;

    @Column(nullable = false)
    private Long valor_consulta;

    public Medico() {
    }

    public Medico(MedicoRequestDTO medicoRequestDTO){
        this.nome = medicoRequestDTO.nome;
        this.cnpj = medicoRequestDTO.cnpj;
        this.crm_estado = medicoRequestDTO.crm_estado;
        this.crm_num = medicoRequestDTO.crm_num;
        this.telefone = medicoRequestDTO.telefone;
        this.email = medicoRequestDTO.email;
        this.especialidade = medicoRequestDTO.especialidade;
        this.sala = medicoRequestDTO.sala;
        this.login = medicoRequestDTO.login;
        this.senha = medicoRequestDTO.senha;
        this.data_disponivel = medicoRequestDTO.data_disponivel;
        this.hora_inicial = medicoRequestDTO.hora_inicial;
        this.hora_final = medicoRequestDTO.hora_final;
        this.valor_consulta = medicoRequestDTO.valor_consulta;

    }

    public Medico(Long id, String nome, String cnpj, String crm_estado, String crm_num, String telefone, String email, String especialidade, String sala, String login, String senha, List<String> data_disponivel, Date hora_inicial, Date hora_final, Long valor_consulta) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.crm_estado = crm_estado;
        this.crm_num = crm_num;
        this.telefone = telefone;
        this.email = email;
        this.especialidade = especialidade;
        this.sala = sala;
        this.login = login;
        this.senha = senha;
        this.data_disponivel = data_disponivel;
        this.hora_inicial = hora_inicial;
        this.hora_final = hora_final;
        this.valor_consulta = valor_consulta;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCrm_estado() {
        return crm_estado;
    }

    public void setCrm_estado(String crm_estado) {
        this.crm_estado = crm_estado;
    }

    public String getCrm_num() {
        return crm_num;
    }

    public void setCrm_num(String crm_num) {
        this.crm_num = crm_num;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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

    public List<String> getData_disponivel() {
        return data_disponivel;
    }

    public void setData_disponivel(List<String> data_disponivel) {
        this.data_disponivel = data_disponivel;
    }

    public Date getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(Date hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public Date getHora_final() {
        return hora_final;
    }

    public void setHora_final(Date hora_final) {
        this.hora_final = hora_final;
    }

    public Long getValor_consulta() {
        return valor_consulta;
    }

    public void setValor_consulta(Long valor_consulta) {
        this.valor_consulta = valor_consulta;
    }
}
