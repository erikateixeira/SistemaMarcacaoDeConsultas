package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Paciente;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PacienteResponseDTO {

    public Long id;
    public String nome;
    public String cpf;
    public String passaporte;
    public String data_nascimento;
    public String nome_responsavel;
    public String cpf_responsavel;
    public String genero;
    public String endereco;
    public String cep;
    public String bairro;
    public String cidade;
    public String estado;
    public String telefone;
    public String email;
    public String plano_saude;
    public String num_plano;
    public String validade_plano;

    public PacienteResponseDTO(Paciente paciente){
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.passaporte = paciente.getPassaporte();

        LocalDate dataNascimento = paciente.getData_nascimento();
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_nascimento = dataNascimento.format(formatoSaida);

        this.nome_responsavel = paciente.getNome_responsavel();
        this.cpf_responsavel = paciente.getCpf_responsavel();
        this.genero = paciente.getGenero();
        this.endereco = paciente.getEndereco();
        this.cep = paciente.getCep();
        this.bairro = paciente.getBairro();
        this.cidade = paciente.getCidade();
        this.estado = paciente.getEstado();
        this.telefone = paciente.getTelefone();
        this.email = paciente.getEmail();
        this.plano_saude = paciente.getPlano_saude();
        this.num_plano = paciente.getNum_plano();

        if(paciente.getValidade_plano()!=null) {
            LocalDate dataValidade = paciente.getValidade_plano();
            this.validade_plano = dataValidade.format(formatoSaida);
        }
    }


}
