package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Consulta;

import java.util.Date;

public class ConsultaResponseDTO {

    public Long id_consulta;
    public Date data_consulta;
    public java.util.Date hora_consulta;
    public boolean retorno_consulta;
    public String nome_medico;
    public String nome_funcionario;
    public String nome_paciente;
    public String especialidade;
    public Long valor_consulta;
    public String plano_saude;

    public ConsultaResponseDTO(Consulta consulta){
        this.id_consulta = consulta.getId();
        this.data_consulta = consulta.getData_consulta();
        this.hora_consulta = consulta.getHora_consulta();
        this.retorno_consulta = consulta.isRetorno_consulta();
        this.nome_medico = consulta.getMedico().getNome();
        this.nome_paciente = consulta.getPaciente().getNome();
        this.nome_funcionario = consulta.getFuncionario().getNome();
        this.especialidade = consulta.getMedico().getEspecialidade();
        this.valor_consulta = consulta.getMedico().getValor_consulta();
        this.plano_saude = consulta.getPaciente().getPlano_saude();

    }



}
