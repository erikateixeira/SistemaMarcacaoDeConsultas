package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findAllByNomeContainingIgnoreCase(String nome);

    Optional<Funcionario> findByNomeContainingIgnoreCase(String nome);

    Optional<Funcionario> findByLogin(String username);

}
