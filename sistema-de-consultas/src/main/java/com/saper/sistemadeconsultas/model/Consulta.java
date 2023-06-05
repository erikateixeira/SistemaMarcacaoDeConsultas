package com.saper.sistemadeconsultas.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;

    @Column(nullable = false)
    private Date data_consulta;

    @Column(nullable = false)
    private Date hora_consulta;

    @Column(nullable = false)
    private boolean retorno_consulta;

    @ManyToOne
    @JoinColumn(name = "nome_medico")
    Medico medico;

    @ManyToOne
    @JoinColumn(name = "nome_funcionario")
    Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "nome_paciente")
    Paciente paciente;

    public Consulta() {
    }

    public Consulta(Long id, Date data_consulta, Date hora_consulta, boolean retorno_consulta, Medico medico, Funcionario funcionario, Paciente paciente) {
        this.id = id;
        this.data_consulta = data_consulta;
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

    public Date getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(Date data_consulta) {
        this.data_consulta = data_consulta;
    }

    public Date getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(Date hora_consulta) {
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
