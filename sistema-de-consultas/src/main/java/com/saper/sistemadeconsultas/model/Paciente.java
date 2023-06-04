package com.saper.sistemadeconsultas.model;

import com.saper.sistemadeconsultas.dto.PacienteRequestDTO;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long id;

    @Column(name = "nome_identidade",
            nullable = false,
            length = 80)
    private String nome_id;

    @Column(name = "nome_preferencia",
            unique = true,
            nullable = false,
            length = 80)
    private String nome;

    @Column(name = "cpf_paciente",
            unique = true,
            length = 14)
    private String cpf;

    @Column(name = "passaporte_paciente",
            unique = true,
            length = 20)
    private String passaporte;

    @Column(name = "data_nascimento_paciente",
            nullable = false,
            length = 10) //  21/07/1998
    private Date data_nascimento;

    @Column(length = 80)
    private String nome_responsavel;

    @Column(length = 14)
    private String cpf_responsavel;

    @Column(length = 30)
    private String genero;

    @Column(name = "endereco_paciente",
            nullable = false)
    private String endereco;

    @Column(name = "cep_paciente",
            nullable = false,
            length = 9) //xxxxx-xxx
    private String cep;

    @Column(name = "bairro_paciente",
            nullable = false,
            length = 80)
    private String bairro;

    @Column(name = "cidade_paciente",
            nullable = false,
            length = 80)
    private String cidade;

    @Column(name = "estado_paciente",
            nullable = false,
            length = 2)
    private String estado;

    @Column(name = "telefone_paciente",
            nullable = false,
            unique = true,
            length = 16)
    private String telefone;

    @Column(name = "email_paciente",
            nullable = false,
            unique = true,
            length = 80)
    private String email;

    @Column(nullable = false,
            length = 40)
    private String plano_saude;

    private Long num_plano;

    private Date validade_plano;

    public Paciente() {
    }

    public Paciente(PacienteRequestDTO pacienteRequestDTO){
        this.nome_id = pacienteRequestDTO.nome_id;
        this.nome = pacienteRequestDTO.nome;
        this.cpf = pacienteRequestDTO.cpf;
        this.passaporte = pacienteRequestDTO.passaporte;
        this.data_nascimento = pacienteRequestDTO.data_nascimento;
        this.nome_responsavel = pacienteRequestDTO.nome_responsavel;
        this.cpf_responsavel = pacienteRequestDTO.cpf_responsavel;
        this.genero = pacienteRequestDTO.genero;
        this.endereco = pacienteRequestDTO.endereco;
        this.cep = pacienteRequestDTO.cep;
        this.bairro = pacienteRequestDTO.bairro;
        this.cidade = pacienteRequestDTO.cidade;
        this.estado = pacienteRequestDTO.estado;
        this.telefone = pacienteRequestDTO.telefone;
        this.email = pacienteRequestDTO.email;
        this.plano_saude = pacienteRequestDTO.plano_saude;
        this.num_plano = pacienteRequestDTO.num_plano;
        this.validade_plano = pacienteRequestDTO.validade_plano;
    }

    public Paciente(Long id, String nome_id, String nome, String cpf, String passaporte, Date data_nascimento, String nome_responsavel, String cpf_responsavel, String genero, String endereco, String cep, String bairro, String cidade, String estado, String telefone, String email, String plano_saude, Long num_plano, Date validade_plano) {
        this.id = id;
        this.nome_id = nome_id;
        this.nome = nome;
        this.cpf = cpf;
        this.passaporte = passaporte;
        this.data_nascimento = data_nascimento;
        this.nome_responsavel = nome_responsavel;
        this.cpf_responsavel = cpf_responsavel;
        this.genero = genero;
        this.endereco = endereco;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
        this.plano_saude = plano_saude;
        this.num_plano = num_plano;
        this.validade_plano = validade_plano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_id() {
        return nome_id;
    }

    public void setNome_id(String nome_id) {
        this.nome_id = nome_id;
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

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }

    public String getCpf_responsavel() {
        return cpf_responsavel;
    }

    public void setCpf_responsavel(String cpf_responsavel) {
        this.cpf_responsavel = cpf_responsavel;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getPlano_saude() {
        return plano_saude;
    }

    public void setPlano_saude(String plano_saude) {
        this.plano_saude = plano_saude;
    }

    public Long getNum_plano() {
        return num_plano;
    }

    public void setNum_plano(Long num_plano) {
        this.num_plano = num_plano;
    }

    public Date getValidade_plano() {
        return validade_plano;
    }

    public void setValidade_plano(Date validade_plano) {
        this.validade_plano = validade_plano;
    }
}
