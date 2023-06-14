package com.saper.sistemadeconsultas.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


public class ConsultaRequestDTO {

    public LocalDate data_consulta;
    public Time hora_consulta;
    public boolean retorno_consulta;
    public String nome_medico;
    public String nome_funcionario;
    public String nome_paciente;

}
