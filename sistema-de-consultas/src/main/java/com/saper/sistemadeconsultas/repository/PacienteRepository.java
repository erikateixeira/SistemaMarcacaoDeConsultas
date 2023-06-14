package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findAllByNomeContainingIgnoreCase(String nome);

    Optional<Paciente> findByNomeContainingIgnoreCase(String nome);

}
