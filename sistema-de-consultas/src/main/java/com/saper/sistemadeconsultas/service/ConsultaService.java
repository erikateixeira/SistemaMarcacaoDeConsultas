package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.enums.DiaSemana;
import com.saper.sistemadeconsultas.exception.exceptions.ConflictStoreException;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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


    public ResponseEntity<Object> getAllConsultasPorDia(LocalDate data) {
        List<Consulta> consultaList = consultaRepository.findByData(data);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas na data: " + data);}
        else {
            List<ConsultaResponseDTO> consultaResponseList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseList);}
    }


    public ResponseEntity<Object> getAllConsultasDoMedicoPorDia(String nome, LocalDate data) {
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));
        List<Consulta> consultaList = consultaRepository.findByDataAndMedicoNomeContainingIgnoreCase(data, nome);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas na data " + data + " para " + nome);}
        else {
            List<ConsultaResponseMedicoDTO> consultaResponseMedicoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseMedicoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseMedicoDTOList);}
    }

    public ResponseEntity<Object> getAllConsultasDoMedicoParaConfirmacao(String nome, LocalDate data) {
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));
        List<Consulta> consultaList = consultaRepository.findByDataAndMedicoNomeContainingIgnoreCase(data, nome);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas na data " + data + " para " + nome);}
        else {
            List<ConsultaResponseConfirmacaoDTO> consultaResponseConfirmacaoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseConfirmacaoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseConfirmacaoDTOList);}
    }


    public ResponseEntity<Object> getConsultaDoPacienteParaAtendimento(String nome, LocalDate data) {
        Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));
        List<Consulta> consultaList = consultaRepository.findByDataAndPacienteNomeContainingIgnoreCase(data, nome);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas na data " + data + " para " + nome);}
        else {
            List<ConsultaResponseAtendimentoDTO> consultaResponseAtendimentoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseAtendimentoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseAtendimentoDTOList);}
    }

    public ResponseEntity<Object> getConsultasDoPaciente(String nome) {
        Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));

        List<Consulta> consultaList = consultaRepository.findByPacienteNomeContainingIgnoreCase(nome);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas para " + nome);}
        else {
            List<ConsultaResponsePacienteDTO> consultaResponsePacienteDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponsePacienteDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponsePacienteDTOList);}
    }

    public List<LocalDate> criarCalendario(ConsultaRequestDTO consultaRequestDTO) {
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

        List<LocalDate> datasValidas = medico.gerarDatasValidas(medico.getDiasDisponiveis());

        return datasValidas;
    }



    @Transactional
    public ResponseEntity<Object> save(ConsultaRequestDTO consultaRequestDTO){
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));
        Funcionario funcionario = funcionarioRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_funcionario).orElseThrow(()-> new NoSuchElementException("Funcionário não encontrado."));
        Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_paciente).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));

        List<LocalDate> datasValidas = criarCalendario(consultaRequestDTO);

        LocalDate data_consulta = LocalDate.parse(consultaRequestDTO.data_consulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if(!datasValidas.contains(data_consulta)){
            throw new IllegalArgumentException("Data inválida para consulta.");
        }

        Consulta consulta = new Consulta();

        consulta.setData(data_consulta);
        consulta.setMedico(medico);
        consulta.setRetorno_consulta(consultaRequestDTO.retorno_consulta);
        consulta.setFuncionario(funcionario);
        consulta.setPaciente(paciente);

        LocalTime hora_consulta_isolada = LocalTime.parse(consultaRequestDTO.hora_consulta, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime hora_consulta = LocalDateTime.of(data_consulta, hora_consulta_isolada);

        boolean consulta_existente = consultaRepository.existsByMedicoAndHora(medico, hora_consulta);
        if (consulta_existente) {
            throw new ConflictStoreException("Conflito: O médico já possui uma consulta agendada para a mesma data e hora.");
        }

        consulta.setHora(hora_consulta);

        try {
            consulta = consultaRepository.save(consulta);
        }
        catch (Exception exception){
            throw new ConflictStoreException("Consulta não pode ser salva devido ao conflito no banco de dados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaResponseDTO(consulta));
    }

    @Transactional
    public ResponseEntity<Object> update(Long id_consulta, ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));

        consulta.setRetorno_consulta(consulta.isRetorno_consulta()); //não pode ser atualizado

        if(consultaRequestDTO.nome_medico!=null){
            Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

            LocalDate nova_data_consulta = consulta.getData();
            LocalDateTime nova_hora_consulta = consulta.getHora();

            if (consultaRequestDTO.data_consulta != null && consultaRequestDTO.hora_consulta != null) {
                nova_data_consulta = LocalDate.parse(consultaRequestDTO.data_consulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime nova_hora_consulta_isolada = LocalTime.parse(consultaRequestDTO.hora_consulta, DateTimeFormatter.ofPattern("HH:mm:ss"));
                nova_hora_consulta = LocalDateTime.of(nova_data_consulta, nova_hora_consulta_isolada);
            }
            boolean consulta_existente = consultaRepository.existsByMedicoAndHora(medico, nova_hora_consulta);
            if (consulta_existente) {
                throw new ConflictStoreException("Conflito: O médico já possui uma consulta agendada para a mesma data e hora.");
            }
            consulta.setMedico(medico);
            consulta.setData(nova_data_consulta);
            consulta.setHora(nova_hora_consulta);
        }

        if(consultaRequestDTO.nome_paciente!=null){
            Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_paciente).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));
            consulta.setPaciente(paciente);
        }
        if(consultaRequestDTO.nome_funcionario!=null){
            Funcionario funcionario = funcionarioRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_funcionario).orElseThrow(()-> new NoSuchElementException("Funcionário não encontrado."));
            consulta.setFuncionario(funcionario);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consultaRepository.save(consulta)));
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id_consulta) {
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));
        consultaRepository.delete(consulta);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
