package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping("/nome")
    public Object getOnlyNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return medicoService.getOnlyNome(nome);
    }

    @GetMapping("/dados")
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return medicoService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@Valid @RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.save(medicoRequestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "id_medico") Long id_medico,
                         @Valid @RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.update(id_medico, medicoRequestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "id_medico") Long id_medico){

        return medicoService.delete(id_medico);
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
