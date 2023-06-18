package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.model.Consulta;
import com.saper.sistemadeconsultas.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByData(LocalDate data);
    List<Consulta> findByDataAndMedicoNomeContainingIgnoreCase(LocalDate data, String nome);
    List<Consulta> findByDataAndPacienteNomeContainingIgnoreCase(LocalDate data, String nome);


}



