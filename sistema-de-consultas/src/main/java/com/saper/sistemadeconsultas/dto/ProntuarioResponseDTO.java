package com.saper.sistemadeconsultas.dto;

import com.saper.sistemadeconsultas.model.Prontuario;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class ProntuarioResponseDTO {

    public Long id_consulta;
    public Long id_prontuario;
    public byte[] pdf;

    public ProntuarioResponseDTO(Prontuario prontuario){
        this.id_prontuario = prontuario.getId();
        this.id_consulta = prontuario.getConsulta().getId();

        /*byte[] pdfBytes = prontuario.getPdf();
        MultipartFile multipartFile = new MockMultipartFile("filename.pdf", pdfBytes);
        this.pdf = multipartFile;*/

        this.pdf = prontuario.getPdf();
    }

}
