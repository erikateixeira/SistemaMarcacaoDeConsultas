package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.dto.ProntuarioRequestDTO;
import com.saper.sistemadeconsultas.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    ProntuarioService prontuarioService;

    @PostMapping
    public Object save(@RequestBody ProntuarioRequestDTO prontuarioRequestDTO) {
        return prontuarioService.save(prontuarioRequestDTO);
    }
}
