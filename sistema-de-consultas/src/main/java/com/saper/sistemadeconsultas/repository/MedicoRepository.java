package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findAllByNomeContainingIgnoreCase(String nome);

    Optional<Medico> findByNomeContainingIgnoreCase(String nome);


}
