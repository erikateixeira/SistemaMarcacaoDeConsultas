package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/dados")
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return funcionarioService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@RequestBody FuncionarioResquestDTO funcionarioResquestDTO){
        return funcionarioService.save(funcionarioResquestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "nome", defaultValue = "") String nome,
                         @RequestBody FuncionarioResquestDTO funcionarioResquestDTO){
        return funcionarioService.update(nome, funcionarioResquestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "nome", defaultValue = "") String nome){
        return funcionarioService.delete(nome);
    }

    @GetMapping("/username")
    public Object getUsername(){
        Object logged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";

        if (logged instanceof Funcionario) {
            username = ((Funcionario)logged).getUsername();
        }
        return username;
    }




}
