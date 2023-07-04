package com.saper.sistemadeconsultas.service;

import ch.qos.logback.core.net.server.Client;
import com.saper.sistemadeconsultas.config.FileStorageConfig;
import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.exception.exceptions.FileProcessingException;
import com.saper.sistemadeconsultas.model.*;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.PacienteRepository;
import com.saper.sistemadeconsultas.repository.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    FileStorageConfig fileStorageConfig;


    public ResponseEntity<Object> getAllProntuarioByNome(String nome) {
        Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));
        List<Prontuario> prontuarioList = prontuarioRepository.findByConsultaPacienteNomeContainingIgnoreCase(nome);

        if (prontuarioList.isEmpty()) {
            throw new NoSuchElementException("Não existem prontuários para: " + nome);
        }
        else {
            List<ProntuarioResponseDTO> prontuarioResponseDTOList = prontuarioList.stream()
                    .map(prontuario -> new ProntuarioResponseDTO(prontuario))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(prontuarioResponseDTOList);
        }
    }


    public ResponseEntity<byte[]> getProntuarioPDF(Long id_prontuario) throws IOException {
        Prontuario prontuario = prontuarioRepository.findById(id_prontuario).orElseThrow(()-> new NoSuchElementException("Prontuário não encontrado."));

        String caminho_arquivo = fileStorageConfig.getUploadPath().resolve(prontuario.getNome()).toString();

        File file = new File(caminho_arquivo);

        if (!file.exists()) {
            throw new NoSuchElementException("Arquivo prontuário não existe.");
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] pdfBytes = IOUtils.toByteArray(fileInputStream);
        fileInputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(prontuario.getNome()).build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<Object> save(Long id_consulta, MultipartFile file) {
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));

        String nome_arquivo;
        Path caminho_arquivo;

        try {
            nome_arquivo = file.getOriginalFilename();
            caminho_arquivo = fileStorageConfig.getUploadPath().resolve(nome_arquivo);

            Files.copy(file.getInputStream(), caminho_arquivo, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new FileProcessingException("Erro ao processar o arquivo PDF");
        }

        Prontuario prontuario = new Prontuario();

        prontuario.setNome(nome_arquivo);
        prontuario.setCaminho(caminho_arquivo.toString());
        prontuario.setConsulta(consulta);

        prontuarioRepository.save(prontuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProntuarioResponseDTO(prontuario));
    }




}
