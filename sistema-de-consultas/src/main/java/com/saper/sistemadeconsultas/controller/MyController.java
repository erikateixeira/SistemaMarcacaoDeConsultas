package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.FuncionarioResponseDTO;
import com.saper.sistemadeconsultas.dto.MedicoResponseDTO;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")

public class MyController {

    @Autowired
    MyService myService;

    @GetMapping("/usuario")
    public ResponseEntity<Object> getUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Funcionario funcionario) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new FuncionarioResponseDTO(myService.getLoggedFuncionario()));
        } else if (principal instanceof Medico medico) {
            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(myService.getLoggedMedico()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
