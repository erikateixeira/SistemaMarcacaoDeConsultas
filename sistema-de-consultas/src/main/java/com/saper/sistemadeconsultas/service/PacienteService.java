package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.PacienteRequestDTO;
import com.saper.sistemadeconsultas.dto.PacienteResponseDTO;
import com.saper.sistemadeconsultas.model.Paciente;
import com.saper.sistemadeconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<Object> getAllByNome(String nome){
        List<Paciente> pacienteList = pacienteRepository.findAllByNomeContaining(nome);

        if(pacienteList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAllByNomeContaining(nome).stream().map(paciente -> new PacienteResponseDTO(paciente)));
        }
    }

    @Transactional
    public ResponseEntity<Object> save(PacienteRequestDTO pacienteRequestDTO) {

        Paciente paciente = new Paciente(pacienteRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(pacienteRepository.save(paciente)));
    }

    //update

    @Transactional
    public ResponseEntity<Object> delete(String nome) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContaining(nome);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
        else {
            pacienteRepository.delete(pacienteOptional.get());

            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }




}
