package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByData(LocalDate data);
    List<Consulta> findByDataAndMedicoNomeContainingIgnoreCase(LocalDate data, String nome);
    List<Consulta> findByDataAndPacienteNomeContainingIgnoreCase(LocalDate data, String nome);
    List<Consulta> findByPacienteNomeContainingIgnoreCase(String nome);
    boolean existsByMedicoAndHora(Medico medico, LocalDateTime hora);

}



