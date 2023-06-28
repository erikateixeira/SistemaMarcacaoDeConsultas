package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.enums.RoleNames;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Role;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.MedicoRepository;
import com.saper.sistemadeconsultas.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    RoleRepository roleRepository;


    public ResponseEntity<Object> getAllByNome(String nome){
        List<Medico> medicoList = medicoRepository.findAllByNomeContainingIgnoreCase(nome);

        if(medicoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(medico -> new MedicoResponseDTO(medico)));
        }
    }

    @Transactional
    public ResponseEntity<Object> save(MedicoRequestDTO medicoRequestDTO) {

        Medico medico = new Medico(medicoRequestDTO);

        setRoleAsMedico(medico);

        return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
    }

    @Transactional
    public ResponseEntity<Object> update(String nome, MedicoRequestDTO medicoRequestDTO) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(nome);

        if(medicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }
        else {
            Medico medico = medicoOptional.get();

            if(medicoRequestDTO.nome!=null){
                medico.setNome(medicoRequestDTO.nome);
            }
            if(medicoRequestDTO.cnpj!=null){
                medico.setCnpj(medicoRequestDTO.cnpj);
            }
            if(medicoRequestDTO.crm_estado!=null){
                medico.setCrm_estado(medicoRequestDTO.crm_estado);
            }
            if(medicoRequestDTO.crm_num!=null){
                medico.setCrm_num(medicoRequestDTO.crm_num);
            }
            if(medicoRequestDTO.telefone!=null){
                medico.setTelefone(medicoRequestDTO.telefone);
            }
            if(medicoRequestDTO.email!=null){
                medico.setEmail(medicoRequestDTO.email);
            }
            if(medicoRequestDTO.especialidade!=null){
                medico.setEspecialidade(medicoRequestDTO.especialidade);
            }
            if(medicoRequestDTO.sala!=null){
                medico.setSala(medicoRequestDTO.sala);
            }
            if(medicoRequestDTO.login!=null){
                medico.setLogin(medicoRequestDTO.login);
            }
            if(medicoRequestDTO.senha!=null){
                medico.setSenha(medicoRequestDTO.senha);
            }
            if(medicoRequestDTO.diasDisponiveis!=null){
                medico.setDiasDisponiveis(medico.convertDiasDisponiveis(medicoRequestDTO.diasDisponiveis));
            }
            if(medicoRequestDTO.hora_inicial!=null){
                LocalDate data_atualizacao = LocalDate.now();
                LocalTime hora_inicial_isolada = medicoRequestDTO.getHora_inicial();
                LocalDateTime hora_inicial = LocalDateTime.of(data_atualizacao, hora_inicial_isolada);
                medico.setHora_inicial(hora_inicial);
            }
            if(medicoRequestDTO.hora_final!=null){
                LocalDate data_atualizacao = LocalDate.now();
                LocalTime hora_final_isolada = medicoRequestDTO.getHora_final();
                LocalDateTime hora_final = LocalDateTime.of(data_atualizacao, hora_final_isolada);
                medico.setHora_final(hora_final);
            }
            if(medicoRequestDTO.valor_consulta!=null){
                medico.setValor_consulta(medicoRequestDTO.valor_consulta);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
        }
    }

    @Transactional
    public ResponseEntity<Object> delete(String nome) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContainingIgnoreCase(nome);

        if (medicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }
        else {
            Medico medico = medicoOptional.get();
            Set<Consulta> consultas = medico.getConsultas();

            for (Consulta consulta : consultas) {
                consulta.setMedico(null);
                consultaRepository.save(consulta);
            }

            medicoRepository.delete(medico);

            for (Consulta consulta : consultas) {
                consultaRepository.save(consulta);
            }

            return ResponseEntity.status(HttpStatus.OK).build();
        }

    }

    public void setRoleAsMedico(Medico medico){
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_MEDICO);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        medico.setRoles(setRole);
    }



}
