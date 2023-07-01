package com.saper.sistemadeconsultas.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.time.LocalDate;

public class FuncionarioResquestDTO {

    @NotBlank
    @Size(min = 10, max = 80)
    public String nome;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Formato de CPF deve ser XXX.XXX.XXX-XX")
    public String cpf;

    @NotBlank
    @Size(min = 5, max = 20)
    public String rg;

    @NotBlank
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data deve ser dd/mm/yyyy")
    public String data_nascimento;

    @NotBlank
    @Size(max = 255)
    public String endereco;

    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato do CEP deve ser XXXXX-XXX")
    public String cep;

    @NotBlank
    @Size(max = 80)
    public String bairro;

    @NotBlank
    @Size(max = 80)
    public String cidade;

    @NotBlank
    @Size(min = 2, max = 2, message = "Estado deve conter 2 caracteres")
    public String estado;

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\) 9 \\d{4}-\\d{4}", message = "Telefone deve ter o formato (XX) 9 XXXX-XXXX")
    public String telefone;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(max = 40)
    /*@Pattern.List({
        @Pattern(regexp = "(?i)admin"),
        @Pattern(regexp = "(?i)recepcionista"),
        @Pattern(regexp = "(?i)auxiliar")
    })*/
    public String funcao;

    @Size(max = 30)
    public String login;

    @Size(max = 15)
    public String senha;

}
