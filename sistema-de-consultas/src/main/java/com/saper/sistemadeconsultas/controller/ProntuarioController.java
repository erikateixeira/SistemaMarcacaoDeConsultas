package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/prontuario")
    public ResponseEntity<byte[]> getProntuarioPDF(@RequestParam(name = "id_prontuario") Long id_prontuario)
            throws IOException {
        return prontuarioService.getProntuarioPDF(id_prontuario);
    }

    @PostMapping
    public Object save(@RequestParam(name = "id_consulta") Long id_consulta,
            @RequestParam(name = "file") MultipartFile file)
            throws IOException {
        return prontuarioService.save(id_consulta, file);
    }
}
