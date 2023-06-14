package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;



@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByDataAndMedicoNomeContaining(Date data, String nome);
    List<Consulta> findByDataAndPacienteNomeContaining(Date data, String nome);

}



