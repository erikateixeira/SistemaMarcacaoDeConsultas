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

    @Column(name = "data_consulta", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    @Column(name = "hora_consulta", nullable = false)
    private LocalDateTime hora;

    @Column(nullable = false)
    private boolean retorno_consulta;

    private boolean confirmacao;

    private boolean autorizacao;

    private boolean pagamento;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id_funcionario")
    Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    Paciente paciente;

    @OneToOne(mappedBy = "consulta", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
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

    public Consulta(Long id, LocalDate data, LocalDateTime hora, boolean retorno_consulta, boolean confirmacao,
            boolean autorizacao, boolean pagamento, Medico medico, Funcionario funcionario, Paciente paciente) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.retorno_consulta = retorno_consulta;
        this.confirmacao = confirmacao;
        this.autorizacao = autorizacao;
        this.pagamento = pagamento;
        this.medico = medico;
        this.funcionario = funcionario;
        this.paciente = paciente;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public boolean isAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(boolean autorizacao) {
        this.autorizacao = autorizacao;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
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

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
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
