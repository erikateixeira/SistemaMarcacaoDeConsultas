package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.PacienteRequestDTO;
import com.saper.sistemadeconsultas.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/nome")
    public Object getOnlyNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return pacienteService.getOnlyNome(nome);
    }

    @GetMapping("/dados")
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return pacienteService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO){
        return pacienteService.save(pacienteRequestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "id_paciente") Long id_paciente,
                         @Valid @RequestBody PacienteRequestDTO pacienteRequestDTO){
        return pacienteService.update(id_paciente, pacienteRequestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "id_paciente") Long id_paciente){
        return pacienteService.delete(id_paciente);
    }



}
