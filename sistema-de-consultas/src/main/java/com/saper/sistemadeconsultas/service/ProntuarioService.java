package com.saper.sistemadeconsultas.service;

import ch.qos.logback.core.net.server.Client;
import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.dto.ProntuarioRequestDTO;
import com.saper.sistemadeconsultas.dto.ProntuarioResponseDTO;
import com.saper.sistemadeconsultas.exception.exceptions.FileProcessingException;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Prontuario;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    ProntuarioRepository prontuarioRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Transactional
    public ResponseEntity<Object> save(ProntuarioRequestDTO prontuarioRequestDTO) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(prontuarioRequestDTO.id_consulta);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }

        byte[] pdfBytes;
        try {
            pdfBytes = prontuarioRequestDTO.pdf.getBytes();
        } catch (IOException e) {
            throw new FileProcessingException("Erro ao processar o arquivo PDF");
        }

        Prontuario prontuario = new Prontuario();

        prontuario.setConsulta(consultaOptional.get());
        prontuario.setPdf(pdfBytes);

        prontuarioRepository.save(prontuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProntuarioResponseDTO(prontuario));

    }




}
