package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.enums.RoleNames;
import com.saper.sistemadeconsultas.exception.exceptions.ConflictStoreException;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Role;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.MedicoRepository;
import com.saper.sistemadeconsultas.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<Object> getOnlyNome(String nome){
        List<Medico> medicoList = medicoRepository.findAllByNomeContainingIgnoreCase(nome);

        if(medicoList.isEmpty()){
            throw new NoSuchElementException("Nenhum médico encontrado com o nome: " + nome);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(medico -> new MedicoResponseNomeDTO(medico)));
        }
    }


    public ResponseEntity<Object> getAllByNome(String nome){
        List<Medico> medicoList = medicoRepository.findAllByNomeContainingIgnoreCase(nome);

        if(medicoList.isEmpty()){
            throw new NoSuchElementException("Nenhum médico encontrado com o nome: " + nome);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(medico -> new MedicoResponseDTO(medico)));
        }
    }

    @Transactional
    public ResponseEntity<Object> save(MedicoRequestDTO medicoRequestDTO) {
        Medico medico = new Medico(medicoRequestDTO);

        setRoleAsMedico(medico);

        try {
            medico = medicoRepository.save(medico);
        }
        catch (Exception exception){
            throw new ConflictStoreException("Médico não pode ser salvo devido ao conflito no banco de dados.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medico));
    }

    @Transactional
    public ResponseEntity<Object> update(Long id_medico, MedicoRequestDTO medicoRequestDTO) {
        Medico medico = medicoRepository.findById(id_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

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
            medico.setSenha(new BCryptPasswordEncoder().encode(medicoRequestDTO.senha));
        }
        if(medicoRequestDTO.diasDisponiveis!=null){
            medico.setDiasDisponiveis(medico.convertDiasDisponiveis(medicoRequestDTO.diasDisponiveis));
        }
        if(medicoRequestDTO.hora_inicial!=null){
            LocalDate data_atualizacao = LocalDate.now();

            String hora_inicial_string = medicoRequestDTO.getHora_inicial();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime hora_inicial_isolada = LocalTime.parse(hora_inicial_string);
            LocalDateTime hora_inicial = LocalDateTime.of(data_atualizacao, hora_inicial_isolada);
            medico.setHora_inicial(hora_inicial);
        }
        if(medicoRequestDTO.hora_final!=null){
            LocalDate data_atualizacao = LocalDate.now();

            String hora_final_string = medicoRequestDTO.getHora_final();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime hora_final_isolada = LocalTime.parse(hora_final_string);
            LocalDateTime hora_final = LocalDateTime.of(data_atualizacao, hora_final_isolada);
            medico.setHora_final(hora_final);
        }
        if(medicoRequestDTO.valor_consulta!=null){
            medico.setValor_consulta(medicoRequestDTO.valor_consulta);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id_medico) {
        Medico medico = medicoRepository.findById(id_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

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

    public void setRoleAsMedico(Medico medico){
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_MEDICO);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        medico.setRoles(setRole);
    }



}
