package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.PacienteRequestDTO;
import com.saper.sistemadeconsultas.dto.PacienteResponseDTO;
import com.saper.sistemadeconsultas.dto.PacienteResponseNomeDTO;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    public ResponseEntity<Object> getOnlyNome(String nome){
        List<Paciente> pacienteList = pacienteRepository.findAllByNomeContainingIgnoreCase(nome);

        if(pacienteList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(paciente -> new PacienteResponseNomeDTO(paciente)));
        }
    }

    public ResponseEntity<Object> getAllByNome(String nome){
        List<Paciente> pacienteList = pacienteRepository.findAllByNomeContainingIgnoreCase(nome);

        if(pacienteList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(paciente -> new PacienteResponseDTO(paciente)));
        }
    }

    @Transactional
    public ResponseEntity<Object> save(PacienteRequestDTO pacienteRequestDTO) {

        Paciente paciente = new Paciente(pacienteRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(pacienteRepository.save(paciente)));
    }

    @Transactional
    public ResponseEntity<Object> update(String nome, PacienteRequestDTO pacienteRequestDTO) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(nome);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
        else {
            Paciente paciente = pacienteOptional.get();

            if(pacienteRequestDTO.nome!=null){
                paciente.setNome(pacienteRequestDTO.nome);
            }
            if(pacienteRequestDTO.cpf!=null){
                paciente.setCpf(pacienteRequestDTO.cpf);
            }
            if(pacienteRequestDTO.passaporte!=null){
                paciente.setPassaporte(pacienteRequestDTO.passaporte);
            }
            if(pacienteRequestDTO.data_nascimento!=null){
                String dataNascimentoStr = pacienteRequestDTO.data_nascimento;
                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                paciente.setData_nascimento(LocalDate.parse(dataNascimentoStr, formatoEntrada));
            }
            if(pacienteRequestDTO.nome_responsavel!=null){
                paciente.setNome_responsavel(pacienteRequestDTO.nome_responsavel);
            }
            if(pacienteRequestDTO.cpf_responsavel!=null){
                paciente.setCpf_responsavel(pacienteRequestDTO.cpf_responsavel);
            }
            if(pacienteRequestDTO.genero!=null){
                paciente.setGenero(pacienteRequestDTO.genero);
            }
            if(pacienteRequestDTO.endereco!=null){
                paciente.setEndereco(pacienteRequestDTO.endereco);
            }
            if(pacienteRequestDTO.cep!=null){
                paciente.setCep(pacienteRequestDTO.cep);
            }
            if(pacienteRequestDTO.bairro!=null){
                paciente.setBairro(pacienteRequestDTO.bairro);
            }
            if(pacienteRequestDTO.cidade!=null){
                paciente.setCidade(pacienteRequestDTO.cidade);
            }
            if(pacienteRequestDTO.estado!=null){
                paciente.setEstado(pacienteRequestDTO.estado);
            }
            if(pacienteRequestDTO.telefone!=null){
                paciente.setTelefone(pacienteRequestDTO.telefone);
            }
            if(pacienteRequestDTO.email!=null){
                paciente.setEmail(pacienteRequestDTO.email);
            }
            if(pacienteRequestDTO.plano_saude!=null){
                paciente.setPlano_saude(pacienteRequestDTO.plano_saude);
            }
            if(pacienteRequestDTO.num_plano!=null){
                paciente.setNum_plano(pacienteRequestDTO.num_plano);
            }
            if(pacienteRequestDTO.validade_plano!=null){
                String dataValidadeStr = pacienteRequestDTO.validade_plano;
                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                paciente.setValidade_plano(LocalDate.parse(dataValidadeStr, formatoEntrada));
            }

            return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(pacienteRepository.save(paciente)));
        }
    }


    @Transactional
    public ResponseEntity<Object> delete(String nome) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(nome);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }
        else {
            Paciente paciente = pacienteOptional.get();
            Set<Consulta> consultas = paciente.getConsultas();

            for (Consulta consulta : consultas) {
                consulta.setPaciente(null);
                consultaRepository.save(consulta);
            }

            pacienteRepository.delete(paciente);

            for (Consulta consulta : consultas) {
                consultaRepository.save(consulta);
            }

            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }


}
