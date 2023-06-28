package com.saper.sistemadeconsultas.model;

import com.saper.sistemadeconsultas.enums.RoleNames;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    RoleNames role;

    @ManyToMany(mappedBy = "roles")
    Set<Medico> medicos;

    @ManyToMany(mappedBy = "roles")
    Set<Funcionario> funcionarios;

    @Override
    public String getAuthority() {
        return role.toString();
    }
}
