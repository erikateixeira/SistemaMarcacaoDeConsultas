package com.saper.sistemadeconsultas.service;

import ch.qos.logback.core.net.server.Client;
import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.exception.exceptions.FileProcessingException;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;
import com.saper.sistemadeconsultas.model.Prontuario;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.PacienteRepository;
import com.saper.sistemadeconsultas.repository.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    ProntuarioRepository prontuarioRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<Object> getAllProntuarioByNome(String nome) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(nome);
        List<Prontuario> prontuarioList = prontuarioRepository.findByConsultaPacienteNomeContainingIgnoreCase(nome);

        if (pacienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }

        if (prontuarioList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem prontuários para o paciente especificado.");
        }

        else {
            List<ProntuarioResponseDTO> prontuarioResponseDTOList = prontuarioList.stream()
                    .map(prontuario -> new ProntuarioResponseDTO(prontuario))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(prontuarioResponseDTOList);
        }

    }

    @Transactional
    public ResponseEntity<Object> save(Long id_consulta, MultipartFile file) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id_consulta);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }

        byte[] pdfBytes;
        try {
            pdfBytes = file.getBytes();
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
