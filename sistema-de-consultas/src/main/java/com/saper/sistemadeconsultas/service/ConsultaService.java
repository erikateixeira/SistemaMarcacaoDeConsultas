package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.enums.DiaSemana;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem consultas dessa data.");
        }

        else {
            List<ConsultaResponseDTO> consultaResponseList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseList);
        }
    }


    public ResponseEntity<Object> getAllConsultasDoMedicoPorDia(String nome, LocalDate data) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(nome);
        List<Consulta> consultaList = consultaRepository.findByDataAndMedicoNomeContainingIgnoreCase(data, nome);

        if (medicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }

        if (consultaList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem consultas dessa data para o médico especificado.");
        }

        else {
            List<ConsultaResponseMedicoDTO> consultaResponseMedicoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseMedicoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseMedicoDTOList);
        }
    }

    public ResponseEntity<Object> getAllConsultasDoMedicoParaConfirmacao(String nome, LocalDate data) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(nome);
        List<Consulta> consultaList = consultaRepository.findByDataAndMedicoNomeContainingIgnoreCase(data, nome);

        if (medicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }

        if (consultaList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem consultas dessa data para o médico especificado.");
        }

        else {
            List<ConsultaResponseConfirmacaoDTO> consultaResponseConfirmacaoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseConfirmacaoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseConfirmacaoDTOList);
        }
    }


    public ResponseEntity<Object> getConsultaDoPacienteParaAtendimento(String nome, LocalDate data) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(nome);
        List<Consulta> consultaList = consultaRepository.findByDataAndPacienteNomeContainingIgnoreCase(data, nome);

        if (pacienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }

        if (consultaList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem consultas dessa data para o paciente especificado.");
        }

        else {
            List<ConsultaResponseAtendimentoDTO> consultaResponseAtendimentoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseAtendimentoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseAtendimentoDTOList);
        }
    }

    public ResponseEntity<Object> getConsultasDoPaciente(String nome) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(nome);
        List<Consulta> consultaList = consultaRepository.findByPacienteNomeContainingIgnoreCase(nome);

        if (pacienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }

        if (consultaList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem consultas para o paciente especificado.");
        }

        else {
            List<ConsultaResponsePacienteDTO> consultaResponsePacienteDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponsePacienteDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponsePacienteDTOList);
        }
    }

    /*public Object criarCalendario(Medico medico) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(medico.getNome());


        DiaSemana[] diasSemana = medicoRequestDTO.getDiasDisponiveis();

        List<LocalDate> datasValidas = gerarDatas

        return Object...
    }*/



    @Transactional
    public ResponseEntity<Object> save(ConsultaRequestDTO consultaRequestDTO){

        Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico);

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_funcionario);

        Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_paciente);

        if(medicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }

        if(funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }


        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }


        Consulta consulta = new Consulta();
        consulta.setMedico(medicoOptional.get());

        consulta.setData(consultaRequestDTO.data_consulta);

        LocalDate data_consulta = consultaRequestDTO.getData_consulta();
        LocalTime hora_consulta_isolada = consultaRequestDTO.getHora_consulta();
        LocalDateTime hora_consulta = LocalDateTime.of(data_consulta, hora_consulta_isolada);
        consulta.setHora_consulta(hora_consulta);

        consulta.setRetorno_consulta(consultaRequestDTO.retorno_consulta);

        consulta.setFuncionario(funcionarioOptional.get());
        consulta.setPaciente(pacienteOptional.get());

        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaResponseDTO(consulta));

    }

    @Transactional
    public ResponseEntity<Object> update(Long id_consulta, ConsultaRequestDTO consultaRequestDTO) {
        Optional<Consulta> consultaOptional  = consultaRepository.findById(id_consulta);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }
        else {
            Consulta consulta = consultaOptional.get();

            if(consultaRequestDTO.nome_medico!=null){
                Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico);

                if(medicoOptional.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
                }
                else {
                    consulta.setMedico(medicoOptional.get());
                }
            }

            if(consultaRequestDTO.nome_paciente!=null){
                Optional<Paciente> pacienteOptional = pacienteRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_paciente);

                if(pacienteOptional.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
                }
                else {
                    consulta.setPaciente(pacienteOptional.get());
                }
            }

            if(consultaRequestDTO.data_consulta!=null){
                consulta.setData(consultaRequestDTO.data_consulta);
            }
            if(consultaRequestDTO.hora_consulta!=null){
                LocalDate data_consulta = consultaRequestDTO.getData_consulta();
                LocalTime hora_consulta_isolada = consultaRequestDTO.getHora_consulta();
                LocalDateTime hora_consulta = LocalDateTime.of(data_consulta, hora_consulta_isolada);
                consulta.setHora_consulta(hora_consulta);
            }
            if(consultaRequestDTO.nome_funcionario!=null){
                Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_funcionario);

                if(funcionarioOptional.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
                }
                else {
                    consulta.setFuncionario(funcionarioOptional.get());
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consultaRepository.save(consulta)));
        }
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id_consulta) {
        Optional<Consulta> consultaOptional  = consultaRepository.findById(id_consulta);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }

        else {
            Consulta consulta = consultaOptional.get();
            consultaRepository.delete(consulta);

            return ResponseEntity.status(HttpStatus.OK).build();

        }

    }



}
