package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.PacienteRequestDTO;
import com.saper.sistemadeconsultas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return pacienteService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@RequestBody PacienteRequestDTO pacienteRequestDTO){
        return pacienteService.save(pacienteRequestDTO);
    }

    //UPDATE

    @DeleteMapping("/{nome}")
    public Object delete(@PathVariable(name = "nome") String nome){
        return pacienteService.delete(nome);
    }



}
