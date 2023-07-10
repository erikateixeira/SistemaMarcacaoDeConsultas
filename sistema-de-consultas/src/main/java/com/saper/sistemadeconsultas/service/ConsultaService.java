package com.saper.sistemadeconsultas.service;

import com.saper.sistemadeconsultas.dto.*;
import com.saper.sistemadeconsultas.enums.DiaSemana;
import com.saper.sistemadeconsultas.exception.exceptions.ConflictStoreException;
import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.model.Paciente;
import com.saper.sistemadeconsultas.repository.ConsultaRepository;
import com.saper.sistemadeconsultas.repository.FuncionarioRepository;
import com.saper.sistemadeconsultas.repository.MedicoRepository;
import com.saper.sistemadeconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;


    public ResponseEntity<Object> getAllConsultasPorDia(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Consulta> consultaList = consultaRepository.findByData(localDate);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas na data: " + localDate);}
        else {
            List<ConsultaResponseDTO> consultaResponseList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseList);}
    }


    public ResponseEntity<Object> getAllConsultasDoMedicoPorDia(String nome, String data) {
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Consulta> consultaList = consultaRepository.findByDataAndMedicoNomeContainingIgnoreCase(localDate, nome);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas na data " + data + " para " + nome);}
        else {
            List<ConsultaResponseMedicoDTO> consultaResponseMedicoDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponseMedicoDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponseMedicoDTOList);}
    }

    public ResponseEntity<Object> getConfirmacao(Long id_consulta) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id_consulta);

        if (consultaOptional.isEmpty()) {
            throw new NoSuchElementException("Não existe consulta para o id" + id_consulta);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseConfirmacaoDTO(consultaOptional.get()));
        }
    }


    public ResponseEntity<Object> getConsultaDoPacienteParaAtendimento(Long id_consulta) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id_consulta);

        if (consultaOptional.isEmpty()) {
            throw new NoSuchElementException("Não existe consulta para o id" + id_consulta);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseAtendimentoDTO(consultaOptional.get()));
        }
    }

    public ResponseEntity<Object> getIniciarConsulta(Long id_consulta) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id_consulta);

        if (consultaOptional.isEmpty()) {
            throw new NoSuchElementException("Não existe consulta para o id" + id_consulta);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseIniciarDTO(consultaOptional.get()));
        }
    }

    public ResponseEntity<Object> getConsultasDoPaciente(String nome) {
        Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));

        List<Consulta> consultaList = consultaRepository.findByPacienteNomeContainingIgnoreCase(nome);

        if (consultaList.isEmpty()) {
            throw new NoSuchElementException("Não existem consultas para " + nome);}
        else {
            List<ConsultaResponsePacienteDTO> consultaResponsePacienteDTOList = consultaList.stream()
                    .map(consulta -> new ConsultaResponsePacienteDTO(consulta))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(consultaResponsePacienteDTOList);}
    }


    //manipulação para listagem de médicos de acordo com a especialidade
    public List<String> medicosDisponiveis(ConsultaRequestDTO consultaRequestDTO){
        String especialidade_medica = consultaRequestDTO.especialidade;

        List<String> medicosEspecialidade = new ArrayList<>();

        if (especialidade_medica.equalsIgnoreCase("cardiologista")) {
            List<Medico> medicosCardiologistas = medicoRepository.findByEspecialidadeIgnoreCase("cardiologista");
            for (Medico medico : medicosCardiologistas) {
                medicosEspecialidade.add(medico.getNome());
            }
        }

        if (especialidade_medica.equalsIgnoreCase("dermatologista")) {
            List<Medico> medicosDermatologista = medicoRepository.findByEspecialidadeIgnoreCase("dermatologista");
            for (Medico medico : medicosDermatologista) {
                medicosEspecialidade.add(medico.getNome());
            }
        }

        if (especialidade_medica.equalsIgnoreCase("ginecologista")) {
            List<Medico> medicosGinecologista = medicoRepository.findByEspecialidadeIgnoreCase("ginecologista");
            for (Medico medico : medicosGinecologista) {
                medicosEspecialidade.add(medico.getNome());
            }
        }

        return medicosEspecialidade;
    }

    //manipulação para lista de dias e horas disponíveis para consulta de acordo com os dias de atendimento médico e horários livres
    public DayOfWeek diaSemanaToDayOfWeek(DiaSemana diaSemana) {
        switch (diaSemana) {
            case SEGUNDA:
                return DayOfWeek.MONDAY;
            case TERCA:
                return DayOfWeek.TUESDAY;
            case QUARTA:
                return DayOfWeek.WEDNESDAY;
            case QUINTA:
                return DayOfWeek.THURSDAY;
            case SEXTA:
                return DayOfWeek.FRIDAY;
            case SABADO:
                return DayOfWeek.SATURDAY;
            case DOMINGO:
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Dia da semana inválido: " + diaSemana);
        }
    }

    public boolean verificarHorariosDisponiveis(LocalDate data) {
        List<Consulta> consultaList = consultaRepository.findByData(data);

        return consultaList.size() < 8;
    }

    public List<LocalDate> gerarDatasValidas(List<DiaSemana> diasDisponiveis) {
        List<LocalDate> datasValidas = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFim = dataAtual.plusMonths(3);

        while (!dataAtual.isAfter(dataFim)) {
            boolean horariosDisponiveis = verificarHorariosDisponiveis(dataAtual);

            if(horariosDisponiveis) {
                for (DiaSemana diaSemana : diasDisponiveis) {
                    if (dataAtual.getDayOfWeek() == diaSemanaToDayOfWeek(diaSemana)) {
                        datasValidas.add(dataAtual);
                        break;
                    }
                }
            }
            dataAtual = dataAtual.plusDays(1);
        }
        return datasValidas;
    }


    public List<String> criarCalendario(ConsultaRequestDTO consultaRequestDTO) {
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

        List<LocalDate> datasValidas = gerarDatasValidas(medico.getDiasDisponiveis());

        DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<String> datasValidasFormatada = datasValidas.stream()
                .map(data -> data.format(formatoSaida))
                .collect(Collectors.toList());

        return datasValidasFormatada;
    }

    public List<LocalTime> criarAgenda(ConsultaRequestDTO consultaRequestDTO) {
        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

        LocalDate data_consulta = LocalDate.parse(consultaRequestDTO.data_consulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<Consulta> consultaList = consultaRepository.findByMedicoAndData(medico, data_consulta);

        List<LocalTime> horariosOcupados = consultaList.stream()
                .map(Consulta::getHora)
                .map(LocalDateTime::toLocalTime)
                .collect(Collectors.toList());

        List<LocalTime> horasValidas = new ArrayList<>();
        LocalTime hora_inicial_sozinha = medico.getHora_inicial().toLocalTime();
        LocalTime hora_final_sozinha = medico.getHora_final().toLocalTime();

        LocalTime hora_disponivel = hora_inicial_sozinha;

        while (!hora_disponivel.isAfter(hora_final_sozinha.minusMinutes(30))) {
            if(!horariosOcupados.contains(hora_disponivel)) {
                horasValidas.add(hora_disponivel);
            }
            hora_disponivel = hora_disponivel.plusMinutes(30);
        }
        return horasValidas;
    }



    @Transactional
    public ResponseEntity<Object> save(ConsultaRequestDTO consultaRequestDTO){
        String especialidade_medica = consultaRequestDTO.especialidade;
        List<String> medicosEspecialidade = medicosDisponiveis(consultaRequestDTO);

        Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

        if (!medicosEspecialidade.contains(medico.getNome())) {
            throw new IllegalArgumentException("Médico não está disponível para a especialidade selecionada.");
        }

        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> datasValidasFormatadas = criarCalendario(consultaRequestDTO);

        List<LocalDate> datasValidas = datasValidasFormatadas.stream()
                .map(data -> LocalDate.parse(data, formatoEntrada))
                .collect(Collectors.toList());

        LocalDate data_consulta = LocalDate.parse(consultaRequestDTO.data_consulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if(!datasValidas.contains(data_consulta)){
            throw new IllegalArgumentException("Data inválida para consulta.");
        }

        Funcionario funcionario = funcionarioRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_funcionario).orElseThrow(()-> new NoSuchElementException("Funcionário não encontrado."));
        Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_paciente).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));

        Consulta consulta = new Consulta();

        consulta.setData(data_consulta);
        consulta.setMedico(medico);
        consulta.setRetorno_consulta(consultaRequestDTO.retorno_consulta);
        consulta.setFuncionario(funcionario);
        consulta.setPaciente(paciente);

        List<LocalTime> horasValidas = criarAgenda(consultaRequestDTO);
        LocalTime hora_consulta_isolada = LocalTime.parse(consultaRequestDTO.hora_consulta, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime hora_consulta = LocalDateTime.of(data_consulta, hora_consulta_isolada);

        boolean consulta_existente = consultaRepository.existsByMedicoAndHora(medico, hora_consulta);
        if (consulta_existente) {
            if(!horasValidas.contains(hora_consulta_isolada)){
                throw new ConflictStoreException("Conflito: O médico já possui uma consulta agendada para a mesma data e hora.");
            }
        }
        else {
            if(!horasValidas.contains(hora_consulta_isolada)){
                throw new IllegalArgumentException("Horário inválido para consulta.");
            }
        }

        consulta.setHora(hora_consulta);

        try {
            consulta = consultaRepository.save(consulta);
        }
        catch (Exception exception){
            throw new ConflictStoreException("Consulta não pode ser salva devido ao conflito no banco de dados.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaResponseDTO(consulta));
    }

    @Transactional
    public ResponseEntity<Boolean> update1(Long id_consulta, Boolean confirmacao){
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));

        consulta.setConfirmacao(confirmacao);
        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.OK).body(confirmacao);
    }

    @Transactional
    public ResponseEntity<Boolean> update2(Long id_consulta, Boolean autorizacao){
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));

        consulta.setAutorizacao(autorizacao);
        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.OK).body(autorizacao);
    }

    @Transactional
    public ResponseEntity<Boolean> update3(Long id_consulta, Boolean pagamento){
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));

        consulta.setPagamento(pagamento);
        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.OK).body(pagamento);
    }

    @Transactional
    public ResponseEntity<Object> update(Long id_consulta, ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));

        consulta.setRetorno_consulta(consulta.isRetorno_consulta()); //não pode ser atualizado

        String especialidade_medica = consultaRequestDTO.especialidade;
        List<String> medicosEspecialidade = medicosDisponiveis(consultaRequestDTO);

        if(consultaRequestDTO.nome_medico!=null) {
            if (!medicosEspecialidade.contains(consultaRequestDTO.nome_medico)) {
                throw new IllegalArgumentException("Médico não está disponível para a especialidade selecionada.");
            }

            Medico medico = medicoRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_medico).orElseThrow(()-> new NoSuchElementException("Médico não encontrado."));

            LocalDate nova_data_consulta;
            LocalTime nova_hora_consulta_isolada;
            LocalDateTime nova_hora_consulta;

            if (consultaRequestDTO.data_consulta != null && consultaRequestDTO.hora_consulta != null) {
                nova_data_consulta = LocalDate.parse(consultaRequestDTO.data_consulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                nova_hora_consulta_isolada = LocalTime.parse(consultaRequestDTO.hora_consulta, DateTimeFormatter.ofPattern("HH:mm:ss"));
                nova_hora_consulta = LocalDateTime.of(nova_data_consulta, nova_hora_consulta_isolada);

                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                List<String> datasValidasFormatadas = criarCalendario(consultaRequestDTO);

                List<LocalDate> datasValidas = datasValidasFormatadas.stream()
                        .map(data -> LocalDate.parse(data, formatoEntrada))
                        .collect(Collectors.toList());

                List<LocalTime> horasValidas = criarAgenda(consultaRequestDTO);

                if (!datasValidas.contains(nova_data_consulta)) {
                    throw new IllegalArgumentException("Data inválida para consulta.");
                }

                boolean consulta_existente = consultaRepository.existsByMedicoAndHora(medico, nova_hora_consulta);
                if (consulta_existente) {
                    throw new ConflictStoreException("Conflito: O médico já possui uma consulta agendada para a mesma data e hora.");
                }
                else {
                    if (!horasValidas.contains(nova_hora_consulta_isolada)) {
                        throw new IllegalArgumentException("Horário inválido para consulta.");
                    }
                }

                consulta.setMedico(medico);
                consulta.setData(nova_data_consulta);
                consulta.setHora(nova_hora_consulta);
            }
        }

        if(consultaRequestDTO.nome_paciente!=null){
            Paciente paciente = pacienteRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_paciente).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado."));
            consulta.setPaciente(paciente);
        }
        if(consultaRequestDTO.nome_funcionario!=null){
            Funcionario funcionario = funcionarioRepository.findByNomeContainingIgnoreCase(consultaRequestDTO.nome_funcionario).orElseThrow(()-> new NoSuchElementException("Funcionário não encontrado."));
            consulta.setFuncionario(funcionario);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consultaRepository.save(consulta)));
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id_consulta) {
        Consulta consulta = consultaRepository.findById(id_consulta).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada."));
        consultaRepository.delete(consulta);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
