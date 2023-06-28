package com.saper.sistemadeconsultas.config.security;

import com.saper.sistemadeconsultas.model.Funcionario;
import com.saper.sistemadeconsultas.model.Medico;
import com.saper.sistemadeconsultas.repository.FuncionarioRepository;
import com.saper.sistemadeconsultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticatorService implements UserDetailsService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    MedicoRepository medicoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByLogin(username);
        Optional<Medico> medicoOptional = medicoRepository.findByLogin(username);

        if (funcionarioOptional.isEmpty() && medicoOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else if (!funcionarioOptional.isEmpty()) {
            return funcionarioOptional.get();
        } else {
            return medicoOptional.get();
        }
    }


}
