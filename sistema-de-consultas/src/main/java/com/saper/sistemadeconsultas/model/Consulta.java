package com.saper.sistemadeconsultas.model;

import com.saper.sistemadeconsultas.dto.ConsultaRequestDTO;
import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;

    @Column(name= "data_consulta",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    @Column(nullable = false)
    private LocalDateTime hora_consulta;

    @Column(nullable = false)
    private boolean retorno_consulta;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id_funcionario")
    Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    Paciente paciente;

    @OneToOne(mappedBy = "consulta", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_prontuario")
    Prontuario prontuario;

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {

        this.prontuario = prontuario;
    }

    public Consulta() {
    }

    public Consulta(Long id, LocalDate data, LocalDateTime hora_consulta, boolean retorno_consulta, Medico medico, Funcionario funcionario, Paciente paciente) {
        this.id = id;
        this.data = data;
        this.hora_consulta = hora_consulta;
        this.retorno_consulta = retorno_consulta;
        this.medico = medico;
        this.funcionario = funcionario;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDateTime getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(LocalDateTime hora_consulta) {
        this.hora_consulta = hora_consulta;
    }

    public boolean isRetorno_consulta() {
        return retorno_consulta;
    }

    public void setRetorno_consulta(boolean retorno_consulta) {
        this.retorno_consulta = retorno_consulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
