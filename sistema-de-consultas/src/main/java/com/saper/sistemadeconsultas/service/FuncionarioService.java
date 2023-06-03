package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.FuncionarioResponseDTO;
import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public ResponseEntity<Object> getAllByNome(String nome){
        List<Funcionario> funcionarioList = funcionarioRepository.findAllByNomeContaining(nome);

        if(funcionarioList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findAllByNomeContaining(nome).stream().map(funcionario -> new FuncionarioResponseDTO(funcionario)));
        }
    }

    public ResponseEntity<Object> save(FuncionarioResquestDTO funcionarioResquestDTO) {

        Funcionario funcionario = new Funcionario(funcionarioResquestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioResponseDTO(funcionarioRepository.save(funcionario)));
    }





}
