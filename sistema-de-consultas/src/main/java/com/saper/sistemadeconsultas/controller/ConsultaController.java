package com.saper.sistemadeconsultas.controller;

import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.dto.FuncionarioResquestDTO;
import com.saper.sistemadeconsultas.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping("/medicos-disponiveis")
    public List<String> getMedicosDisponiveis(@RequestParam("especialidade") String especialidade) {
        ConsultaRequestDTO consultaRequestDTO = new ConsultaRequestDTO();
        consultaRequestDTO.setEspecialidade(especialidade);

        return consultaService.medicosDisponiveis(consultaRequestDTO);
    }

    @GetMapping("/datas-disponiveis")
    public List<String> getHorariosDisponiveis(@RequestParam("nome_medico") String nome_medico) {
        ConsultaRequestDTO consultaRequestDTO = new ConsultaRequestDTO();
        consultaRequestDTO.setNome_medico(nome_medico);

        return consultaService.criarCalendario(consultaRequestDTO);
    }

    @GetMapping("/horas-validas")
    public List<LocalTime> getHorasValidas(
            @RequestParam("nome_medico") String nome_medico,
            @RequestParam("data_consulta") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data_consulta) {

        ConsultaRequestDTO consultaRequestDTO = new ConsultaRequestDTO();
        consultaRequestDTO.setNome_medico(nome_medico);
        consultaRequestDTO.setData_consulta(data_consulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return consultaService.criarAgenda(consultaRequestDTO);
    }


    @GetMapping("/dia")
    public Object getAllConsultasPorDia(@RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") String data){
        return consultaService.getAllConsultasPorDia(data);
    }

    @GetMapping("/lista")
    public Object getAllConsultasDoMedicoPorDia(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                 @RequestParam(name = "data") @DateTimeFormat(pattern = "dd/MM/yyyy") String data) {
        return consultaService.getAllConsultasDoMedicoPorDia(nome, data);
    }

    @GetMapping("/confirmacao")
    public Object getConfirmacao(@RequestParam(name = "id_consulta") Long id_consulta) {
        return consultaService.getConfirmacao(id_consulta);
    }

    @GetMapping("/atendimento")
    public Object getConsultaDoPacienteParaAtendimento(@RequestParam(name = "id_consulta") Long id_consulta) {
        return consultaService.getConsultaDoPacienteParaAtendimento(id_consulta);
    }

    @GetMapping("/iniciar")
    public Object getIniciarConsulta(@RequestParam(name = "id_consulta") Long id_consulta) {
        return consultaService.getIniciarConsulta(id_consulta);
    }

    @GetMapping("/paciente")
    public Object getConsultaDoPaciente(@RequestParam(name = "nome", defaultValue = "") String nome) {
        return consultaService.getConsultasDoPaciente(nome);
    }

    @PostMapping
    public Object save(@Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        return consultaService.save(consultaRequestDTO);
    }

    @PutMapping("/confirmacao")
    public Object update1(@RequestParam(name = "id_consulta") Long id_consulta,
                          @RequestParam Boolean confirmacao){

        return consultaService.update1(id_consulta, confirmacao);
    }

    @PutMapping("/autorizacao")
    public Object update2(@RequestParam(name = "id_consulta") Long id_consulta,
                         @RequestParam Boolean autorizacao){

        return consultaService.update2(id_consulta, autorizacao);
    }

    @PutMapping("/pagamento")
    public Object update3(@RequestParam(name = "id_consulta") Long id_consulta,
                         @RequestParam Boolean pagamento){

        return consultaService.update3(id_consulta, pagamento);
    }



    @PutMapping
    public Object update(@RequestParam(name = "id_consulta") Long id_consulta,
                        @Valid @RequestBody ConsultaRequestDTO consultaRequestDTO){
        return consultaService.update(id_consulta, consultaRequestDTO);
    }

    @DeleteMapping
    public Object delete(@RequestParam(name = "id_consulta") Long id_consulta){
        return consultaService.delete(id_consulta);
    }




}
