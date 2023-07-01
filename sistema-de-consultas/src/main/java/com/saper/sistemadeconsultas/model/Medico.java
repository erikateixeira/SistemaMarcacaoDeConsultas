package com.saper.sistemadeconsultas.model;

import com.saper.sistemadeconsultas.dto.MedicoRequestDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.saper.sistemadeconsultas.enums.DiaSemana;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Entity
public class Medico implements UserDetails {

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
            length = 30)
    private String login;

    @Column(name = "senha_medico",
            nullable = false,
            length = 80)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "medico_role",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    Set<Role> roles;

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

        if(medicoRequestDTO.senha!=null) {
            this.senha = new BCryptPasswordEncoder().encode(medicoRequestDTO.senha);
        }

        this.diasDisponiveis = convertDiasDisponiveis(medicoRequestDTO.diasDisponiveis);


        LocalDate data_cadastro = LocalDate.now();

        String hora_inicial_string = medicoRequestDTO.getHora_inicial();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(new Locale("pt", "BR"));
        LocalTime hora_inicial_isolada = LocalTime.parse(hora_inicial_string);
        LocalDateTime hora_inicial = LocalDateTime.of(data_cadastro, hora_inicial_isolada);
        this.hora_inicial = hora_inicial;

        String hora_final_string = medicoRequestDTO.getHora_final();
        LocalTime hora_final_isolada = LocalTime.parse(hora_final_string);
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

    /*public List<LocalDate> gerarDatasValidas(List<DiaSemana> diasDisponiveis) {
        List<LocalDate> datasValidas = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFim = dataAtual.plusMonths(3);

        while (!dataAtual.isAfter(dataFim)) {
            for (DiaSemana diaSemana : diasDisponiveis) {
                if (dataAtual.getDayOfWeek() == diaSemanaToDayOfWeek(diaSemana)) {
                    datasValidas.add(dataAtual);
                    break;
                }
            }
            dataAtual = dataAtual.plusDays(1);
        }

        return datasValidas;
    }

    public DayOfWeek diaSemanaToDayOfWeek(DiaSemana diaSemana) {
        switch (diaSemana) {
            case SEGUNDA:
                return DayOfWeek.MONDAY;
            case TERCA:
                return DayOfWeek.TUESDAY;
            case QUARTA:
                return DayOfWeek.WEDNESDAY;
            case QUINTA:
                return DayOfWeek.THURSDAY;
            case SEXTA:
                return DayOfWeek.FRIDAY;
            case SABADO:
                return DayOfWeek.SATURDAY;
            case DOMINGO:
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Dia da semana inválido: " + diaSemana);
        }
    }*/


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
