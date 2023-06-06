package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    public ResponseEntity<Object> getAllByNome(String nome){
        List<Medico> medicoList = medicoRepository.findAllByNomeContaining(nome);

        if(medicoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAllByNomeContaining(nome).stream().map(medico -> new MedicoResponseDTO(medico)));
        }
    }

    @Transactional
    public ResponseEntity<Object> save(MedicoRequestDTO medicoRequestDTO) {

        Medico medico = new Medico(medicoRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
    }

    @Transactional
    public ResponseEntity<Object> update(String nome, MedicoRequestDTO medicoRequestDTO) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContaining(nome);

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
            if(medicoRequestDTO.data_disponivel!=null){
                medico.setData_disponivel(medicoRequestDTO.data_disponivel);
            }
            if(medicoRequestDTO.hora_inicial!=null){
                medico.setHora_inicial(medicoRequestDTO.hora_inicial);
            }
            if(medicoRequestDTO.hora_final!=null){
                medico.setHora_final(medicoRequestDTO.hora_final);
            }
            if(medicoRequestDTO.valor_consulta!=null){
                medico.setValor_consulta(medicoRequestDTO.valor_consulta);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
        }
    }

    @Transactional
    public ResponseEntity<Object> delete(String nome) {
        Optional<Medico> medicoOptional = medicoRepository.findByNomeContaining(nome);

        if(medicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado.");
        }
        else {
            medicoRepository.delete(medicoOptional.get());

            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }






}