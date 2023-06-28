package com.saper.sistemadeconsultas.repository;

import com.saper.sistemadeconsultas.enums.RoleNames;
import com.saper.sistemadeconsultas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleNames roleNames);

}
