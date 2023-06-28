package com.saper.sistemadeconsultas.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProntuarioRequestDTO {

    public Long id_consulta;
    public MultipartFile pdf;

    public Long getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(Long id_consulta) {
        this.id_consulta = id_consulta;
    }

    public MultipartFile getPdf() {
        return pdf;
    }

    public void setPdf(MultipartFile pdf) {
        this.pdf = pdf;
    }
}
