package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import com.saper.sistemadeconsultas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping
    public Object getAllByNome(@RequestParam(name = "nome", defaultValue = "") String nome){
        return medicoService.getAllByNome(nome);
    }

    @PostMapping
    public Object save(@RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.save(medicoRequestDTO);
    }

    @PutMapping("/{nome}")
    public Object update(@PathVariable(name = "nome") String nome,
                         @RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.update(nome, medicoRequestDTO);
    }

    @DeleteMapping("/{nome}")
    public Object delete(@PathVariable(name = "nome") String nome){

        return medicoService.delete(nome);
    }

}
