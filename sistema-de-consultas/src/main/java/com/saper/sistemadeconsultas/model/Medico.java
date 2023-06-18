package com.saper.sistemadeconsultas.model;

import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long id;

    @Column(name = "nome_medico",
            unique = true,
            nullable = false,
            length = 80)
    private String nome;

    @Column(unique = true,
            nullable = false,
            length = 18) // XX.XXX.XXX/000X-XX
    private String cnpj;

    @Column(nullable = false,
            length = 2) //CRM-CE
    private String crm_estado;

    @Column(nullable = false,
            length = 6) // CRM-CE 123456
    private String crm_num;

    @Column(name = "telefone_medico",
            nullable = false,
            unique = true,
            length = 16)
    private String telefone;

    @Column(name = "email_medico",
            nullable = false,
            unique = true,
            length = 80)
    private String email;

    @Column(nullable = false,
            length = 30)
    private String especialidade;

    @Column(nullable = false,
            length = 2)
    private String sala;

    @Column(name = "login_medico",
            nullable = false,
            unique = true,
            length = 45)
    private String login;

    @Column(name = "senha_medico",
            nullable = false,
            length = 15)
    private String senha;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime hora_inicial;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime hora_final;

    @Column(nullable = false)
    private Long valor_consulta;

    @OneToMany(mappedBy = "medico", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Set<Consulta> consultas;

    @ElementCollection(targetClass = DiaSemana.class)
    @CollectionTable(name = "medico_dia_semana",
            joinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id_medico"))
    @Column(name = "dia_semana")
    @Enumerated(EnumType.STRING)
    private List<DiaSemana> diasDisponiveis;

    public List<DiaSemana> getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public void setDiasDisponiveis(List<DiaSemana> diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }

    public List<DiaSemana> convertDiasDisponiveis(List<String> diasDisponiveis) {
        List<DiaSemana> diasSemanaEnum = new ArrayList<>();
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diasDisponiveis.contains(diaSemana.toString())) {
                diasSemanaEnum.add(diaSemana);
            }
        }
        return diasSemanaEnum;
    }



    public Medico() {
    }

    public Medico(MedicoRequestDTO medicoRequestDTO){
        this.nome = medicoRequestDTO.nome;
        this.cnpj = medicoRequestDTO.cnpj;
        this.crm_estado = medicoRequestDTO.crm_estado;
        this.crm_num = medicoRequestDTO.crm_num;
        this.telefone = medicoRequestDTO.telefone;
        this.email = medicoRequestDTO.email;
        this.especialidade = medicoRequestDTO.especialidade;
        this.sala = medicoRequestDTO.sala;
        this.login = medicoRequestDTO.login;
        this.senha = medicoRequestDTO.senha;
        this.diasDisponiveis = convertDiasDisponiveis(medicoRequestDTO.diasDisponiveis);


        LocalDate data_cadastro = LocalDate.now();

        LocalTime hora_inicial_isolada = medicoRequestDTO.getHora_inicial();
        LocalDateTime hora_inicial = LocalDateTime.of(data_cadastro, hora_inicial_isolada);
        this.hora_inicial = hora_inicial;

        LocalTime hora_final_isolada = medicoRequestDTO.getHora_final();
        LocalDateTime hora_final = LocalDateTime.of(data_cadastro, hora_final_isolada);
        this.hora_final = hora_final;

        this.valor_consulta = medicoRequestDTO.valor_consulta;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }


    public Medico(Long id, String nome, String cnpj, String crm_estado, String crm_num, String telefone, String email, String especialidade, String sala, String login, String senha, LocalDateTime hora_inicial, LocalDateTime hora_final, Long valor_consulta) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.crm_estado = crm_estado;
        this.crm_num = crm_num;
        this.telefone = telefone;
        this.email = email;
        this.especialidade = especialidade;
        this.sala = sala;
        this.login = login;
        this.senha = senha;
        this.hora_inicial = hora_inicial;
        this.hora_final = hora_final;
        this.valor_consulta = valor_consulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCrm_estado() {
        return crm_estado;
    }

    public void setCrm_estado(String crm_estado) {
        this.crm_estado = crm_estado;
    }

    public String getCrm_num() {
        return crm_num;
    }

    public void setCrm_num(String crm_num) {
        this.crm_num = crm_num;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(LocalDateTime hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public LocalDateTime getHora_final() {
        return hora_final;
    }

    public void setHora_final(LocalDateTime hora_final) {
        this.hora_final = hora_final;
    }

    public Long getValor_consulta() {
        return valor_consulta;
    }

    public void setValor_consulta(Long valor_consulta) {
        this.valor_consulta = valor_consulta;
    }
}
