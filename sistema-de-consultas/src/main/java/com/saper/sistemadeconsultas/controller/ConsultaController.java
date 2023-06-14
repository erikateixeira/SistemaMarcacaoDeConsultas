package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping("/lista")
    public Object ggetAllConsultasDoMedicoPorDia(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                 @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") Date data) {
        return consultaService.getAllConsultasDoMedicoPorDia(nome, data);
    }

    @GetMapping("/confirmacao")
    public Object getAllConsultasDoMedicoParaConfirmacao(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                         @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") Date data) {
        return consultaService.getAllConsultasDoMedicoParaConfirmacao(nome, data);
    }

    @GetMapping("/atendimento")
    public Object getConsultaDoPacienteParaAtendimento(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                       @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") Date data) {
        return consultaService.getConsultaDoPacienteParaAtendimento(nome, data);
    }

    @PostMapping
    public Object save(@RequestBody ConsultaRequestDTO consultaRequestDTO) {
        return consultaService.save(consultaRequestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "nome_paciente", defaultValue = "") String nome_paciente,
                         @RequestParam(name = "nome_medico", defaultValue = "") String nome_medico,
                         @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") Date data,
                         @RequestBody ConsultaRequestDTO consultaRequestDTO){
        return consultaService.update(nome_paciente, nome_medico, data, consultaRequestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "nome_paciente", defaultValue = "") String nome_paciente,
                         @RequestParam(name = "nome_medico", defaultValue = "") String nome_medico,
                         @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") Date data){

        return consultaService.delete(nome_paciente, nome_medico, data);
    }




}
