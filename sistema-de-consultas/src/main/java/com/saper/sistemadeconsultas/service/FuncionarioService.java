package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.FuncionarioResponseDTO;
import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.enums.RoleNames;
import com.saper.sistemadeconsultas.model.*;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.FuncionarioRepository;
import com.saper.sistemadeconsultas.repository.RoleRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    RoleRepository roleRepository;


    public ResponseEntity<Object> getAllByNome(String nome){
        List<Funcionario> funcionarioList = funcionarioRepository.findAllByNomeContainingIgnoreCase(nome);

        if(funcionarioList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(funcionario -> new FuncionarioResponseDTO(funcionario)));
        }
    }

    @Transactional
    public ResponseEntity<Object> save(FuncionarioResquestDTO funcionarioResquestDTO) {

        Funcionario funcionario = new Funcionario(funcionarioResquestDTO);

        if (funcionarioResquestDTO.funcao.equals("ADMIN")){
            setRoleAsAdmin(funcionario);
        }
        if(funcionarioResquestDTO.funcao.equals("RECEPCIONISTA")){
            setRoleAsRecepcionista(funcionario);
        }
        if(funcionarioResquestDTO.funcao.equals("AUXILIAR")){
            setRoleAsAuxiliar(funcionario);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioResponseDTO(funcionarioRepository.save(funcionario)));
    }


    @Transactional
    public ResponseEntity<Object> update(String nome, FuncionarioResquestDTO funcionarioResquestDTO) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByNomeContainingIgnoreCase(nome);

        if(funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        else {
            Funcionario funcionario = funcionarioOptional.get();

            if(funcionarioResquestDTO.nome!=null){
                funcionario.setNome(funcionarioResquestDTO.nome);
            }
            if(funcionarioResquestDTO.cpf!=null){
                funcionario.setCpf(funcionarioResquestDTO.cpf);
            }
            if(funcionarioResquestDTO.rg!=null){
                funcionario.setRg(funcionarioResquestDTO.rg);
            }
            if(funcionarioResquestDTO.data_nascimento!=null){
                funcionario.setData_nascimento(funcionarioResquestDTO.data_nascimento);
            }
            if(funcionarioResquestDTO.endereco!=null){
                funcionario.setEndereco(funcionarioResquestDTO.endereco);
            }
            if(funcionarioResquestDTO.cep!=null){
                funcionario.setCep(funcionarioResquestDTO.cep);
            }
            if(funcionarioResquestDTO.bairro!=null){
                funcionario.setBairro(funcionarioResquestDTO.bairro);
            }
            if(funcionarioResquestDTO.cidade!=null){
                funcionario.setCidade(funcionarioResquestDTO.cidade);
            }
            if(funcionarioResquestDTO.estado!=null){
                funcionario.setEstado(funcionarioResquestDTO.estado);
            }
            if(funcionarioResquestDTO.telefone!=null){
                funcionario.setTelefone(funcionarioResquestDTO.telefone);
            }
            if(funcionarioResquestDTO.email!=null){
                funcionario.setEmail(funcionarioResquestDTO.email);
            }

            if(funcionarioResquestDTO.funcao!=null){
                funcionario.setFuncao(funcionarioResquestDTO.funcao);

                if (funcionarioResquestDTO.funcao.equals("ADMIN")){
                    setRoleAsAdmin(funcionario);
                }
                if(funcionarioResquestDTO.funcao.equals("RECEPCIONISTA")){
                    setRoleAsRecepcionista(funcionario);
                }
                if(funcionarioResquestDTO.funcao.equals("AUXILIAR")){
                    setRoleAsAuxiliar(funcionario);
                }
            }

            if(funcionarioResquestDTO.login!=null){
                funcionario.setLogin(funcionarioResquestDTO.login);
            }
            if(funcionarioResquestDTO.senha!=null){
                funcionario.setSenha(funcionarioResquestDTO.senha);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioResponseDTO(funcionarioRepository.save(funcionario)));
        }
    }

    @Transactional
    public ResponseEntity<Object> delete(String nome) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByNomeContainingIgnoreCase(nome);

        if(funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        else {
            Funcionario funcionario = funcionarioOptional.get();
            Set<Consulta> consultas = funcionario.getConsultas();

            for (Consulta consulta : consultas) {
                consulta.setFuncionario(null);
                consultaRepository.save(consulta);
            }

            funcionarioRepository.delete(funcionario);

            for (Consulta consulta : consultas) {
                consultaRepository.save(consulta);
            }

            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    public void setRoleAsAdmin(Funcionario funcionario){
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_ADMIN);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        funcionario.setRoles(setRole);
    }

    public void setRoleAsRecepcionista(Funcionario funcionario){
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_RECEPCIONISTA);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        funcionario.setRoles(setRole);
    }

    public void setRoleAsAuxiliar(Funcionario funcionario){
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_AUXILIAR);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        funcionario.setRoles(setRole);
    }


}
