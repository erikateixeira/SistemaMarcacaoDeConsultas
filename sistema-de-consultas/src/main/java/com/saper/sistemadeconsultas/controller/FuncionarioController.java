package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/nome")
    public Object getOnlyNome(@RequestParam(name = "nome", defaultValue = "") String nome) {
        return funcionarioService.getOnlyNome(nome);
    }

    @GetMapping("/dados")
    // @CrossOrigin(origins = "*", allowedHeaders = "*")
    @CrossOrigin(origins = "http://localhost:5173")
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome) {
        return funcionarioService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@Valid @RequestBody FuncionarioResquestDTO funcionarioResquestDTO) {
        return funcionarioService.save(funcionarioResquestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "id_funcionario") Long id_funcionario,
            @Valid @RequestBody FuncionarioResquestDTO funcionarioResquestDTO) {
        return funcionarioService.update(id_funcionario, funcionarioResquestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "id_funcionario") Long id_funcionario) {
        return funcionarioService.delete(id_funcionario);
    }

    @GetMapping("/username")
    public Object getUsername() {
        Object logged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";

        if (logged instanceof Funcionario) {
            username = ((Funcionario) logged).getUsername();
        }
        return username;
    }

}
