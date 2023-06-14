package com.saper.sistemadeconsultas.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;

    @Column(name= "data_consulta",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @Column(nullable = false)
    private Time hora_consulta;

    @Column(nullable = false)
    private boolean retorno_consulta;

    @ManyToOne
    @JoinColumn(name = "nome_medico", referencedColumnName = "nome_medico", nullable = false)
    Medico medico;

    @ManyToOne
    @JoinColumn(name = "nome_funcionario", referencedColumnName = "nome_funcionario", nullable = false)
    Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "nome_paciente", referencedColumnName = "nome_paciente", nullable = false)
    Paciente paciente;


    public Consulta() {
    }

    public Consulta(Long id, LocalDate data, Time hora_consulta, boolean retorno_consulta, Medico medico, Funcionario funcionario, Paciente paciente) {
        this.id = id;
        this.data = data;
        this.hora_consulta = new Time(hora_consulta.getTime());
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

    public Time getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(Time hora_consulta) {
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
