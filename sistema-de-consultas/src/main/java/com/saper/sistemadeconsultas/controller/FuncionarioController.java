package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return funcionarioService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@RequestBody FuncionarioResquestDTO funcionarioResquestDTO){
        return funcionarioService.save(funcionarioResquestDTO);
    }




}
