package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public Funcionario getLoggedFuncionario(){
        return (Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Medico getLoggedMedico(){
        return (Medico) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
