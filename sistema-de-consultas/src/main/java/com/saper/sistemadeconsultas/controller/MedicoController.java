package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping("/dados")
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return medicoService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.save(medicoRequestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "nome", defaultValue = "") String nome,
                         @RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.update(nome, medicoRequestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "nome", defaultValue = "") String nome){

        return medicoService.delete(nome);
    }

    @GetMapping("/username")
    public Object getUsername(){
        Object logged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";

        if (logged instanceof Medico) {
            username = ((Medico)logged).getUsername();
        }
        return username;
    }

}
