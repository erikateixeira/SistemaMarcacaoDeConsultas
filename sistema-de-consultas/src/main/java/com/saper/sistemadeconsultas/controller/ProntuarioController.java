package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    ProntuarioService prontuarioService;

    @GetMapping("/paciente")
    public Object getAllProntuarioByNome(@RequestParam(name = "nome", defaultValue = "") String nome) {
        return prontuarioService.getAllProntuarioByNome(nome);
    }

    @PostMapping
    public Object save(@RequestParam("id_consulta") Long id_consulta,
                       @RequestParam("file")MultipartFile file)
            throws IOException {
        return prontuarioService.save(id_consulta, file);
    }
}
