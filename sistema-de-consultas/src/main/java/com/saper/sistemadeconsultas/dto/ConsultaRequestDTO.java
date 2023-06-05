package com.saper.sistemadeconsultas.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class ConsultaRequestDTO {

    public Date data_consulta;
    public Date hora_consulta;
    public boolean retorno_consulta;
    public String nome_medico;
    public String nome_funcionario;
    public String nome_paciente;

}
