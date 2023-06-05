package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Paciente;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.FuncionarioRepository;
import com.saper.sistemadeconsultas.repository.MedicoRepository;
import com.saper.sistemadeconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;

   /* @Transactional
    public ResponseEntity<Object> save(ConsultaRequestDTO consultaRequestDTO){
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContaining(consultaRequestDTO.nome_paciente);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }

        Consulta consulta = new Consulta();

    }*/

}
