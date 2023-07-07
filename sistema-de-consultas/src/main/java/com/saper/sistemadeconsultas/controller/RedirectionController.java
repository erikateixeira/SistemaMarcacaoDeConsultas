package com.saper.sistemadeconsultas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RedirectionController {

    @GetMapping("/redirection")
    public ResponseEntity<Object> redirectToAnotherServer() {
        // Realizar lógica necessária antes do redirecionamento, se necessário

        // Redirecionar para o outro servidor
        String redirectUrl = "http://localhost:5175/";
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", redirectUrl).build();
    }
}
