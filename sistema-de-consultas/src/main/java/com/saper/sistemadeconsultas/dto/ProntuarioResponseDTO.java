package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Prontuario;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ProntuarioResponseDTO {

    public Long id_prontuario;
    public Long id_consulta;
    public String data_consulta;
    public String nome_medico;


    public ProntuarioResponseDTO(Prontuario prontuario){
        this.id_prontuario = prontuario.getId();
        this.id_consulta = prontuario.getConsulta().getId();

        LocalDate dataConsulta = prontuario.getConsulta().getData();
        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_consulta = dataConsulta.format(formatoSaida);

        this.nome_medico = prontuario.getConsulta().getMedico().getNome();
    }

}
