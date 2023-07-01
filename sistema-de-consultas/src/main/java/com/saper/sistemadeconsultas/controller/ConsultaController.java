package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping("/dia")
    public Object getAllConsultasPorDia(@RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data){
        return consultaService.getAllConsultasPorDia(data);
    }

    @GetMapping("/lista")
    public Object getAllConsultasDoMedicoPorDia(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                 @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        return consultaService.getAllConsultasDoMedicoPorDia(nome, data);
    }

    @GetMapping("/confirmacao")
    public Object getAllConsultasDoMedicoParaConfirmacao(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                         @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        return consultaService.getAllConsultasDoMedicoParaConfirmacao(nome, data);
    }

    @GetMapping("/atendimento")
    public Object getConsultaDoPacienteParaAtendimento(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                       @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        return consultaService.getConsultaDoPacienteParaAtendimento(nome, data);
    }

    @GetMapping("/paciente")
    public Object getConsultaDoPaciente(@RequestParam(name = "nome", defaultValue = "") String nome) {
        return consultaService.getConsultasDoPaciente(nome);
    }

    @PostMapping
    public Object save(@Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        return consultaService.save(consultaRequestDTO);
    }

    @PutMapping
    public Object update(@RequestParam(name = "nome_paciente", defaultValue = "") String nome_paciente,
                         @RequestParam(name = "nome_medico", defaultValue = "") String nome_medico,
                         @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data,
                         @Valid @RequestBody ConsultaRequestDTO consultaRequestDTO){
        return consultaService.update(nome_paciente, nome_medico, data, consultaRequestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "nome_paciente", defaultValue = "") String nome_paciente,
                         @RequestParam(name = "nome_medico", defaultValue = "") String nome_medico,
                         @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data){

        return consultaService.delete(nome_paciente, nome_medico, data);
    }




}
