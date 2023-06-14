package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class ConsultaResponseConfirmacaoDTO {

    public Long id_consulta;
    public String nome_medico;
    public String especialidade;
    public LocalDate data_consulta;
    public Time hora_consulta;
    public boolean retorno_consulta;
    public String nome_paciente;
    public String telefone_paciente;
    public Long valor_consulta;
    public String plano_saude;
    public String nome_funcionario;


    public ConsultaResponseConfirmacaoDTO(Consulta consulta){
        this.id_consulta = consulta.getId();
        this.nome_medico = consulta.getMedico().getNome();
        this.especialidade = consulta.getMedico().getEspecialidade();
        this.data_consulta = consulta.getData();
        this.hora_consulta = consulta.getHora_consulta();
        this.retorno_consulta = consulta.isRetorno_consulta();
        this.nome_paciente = consulta.getPaciente().getNome();
        this.telefone_paciente = consulta.getPaciente().getTelefone();
        this.valor_consulta = consulta.getMedico().getValor_consulta();
        this.plano_saude = consulta.getPaciente().getPlano_saude();
        this.nome_funcionario = consulta.getFuncionario().getNome();

    }



}
