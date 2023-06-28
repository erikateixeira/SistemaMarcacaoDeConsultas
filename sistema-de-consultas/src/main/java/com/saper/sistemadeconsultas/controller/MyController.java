package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.FuncionarioResponseDTO;
import com.saper.sistemadeconsultas.dto.MedicoResponseDTO;
import com.saper.sistemadeconsultas.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {

    @Autowired
    MyService myService;

    //endpoint para puxar automaticamente o nome do funcionário logado e retornar no campo nome_funcionário do PostMapping e PutMapping do ConsultaController;
    @GetMapping("/funcionario")
    public ResponseEntity<Object> getFuncionario(){
        return ResponseEntity.status(HttpStatus.OK).body(myService.getLoggedFuncionario().getNome());
    }

    //endpoint para puxar automaticamente o nome do médico logado e retornar no campo nome_medico do GetMapping("/lista) do ConsultaController;
    @GetMapping("/medico")
    public ResponseEntity<Object> getMedico(){
        return ResponseEntity.status(HttpStatus.OK).body(myService.getLoggedMedico().getNome());
    }



}
