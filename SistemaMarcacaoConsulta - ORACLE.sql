-- -----------------------------------------------------
-- Table   Funcionario 
-- -----------------------------------------------------
CREATE TABLE   Funcionario  (
   idFuncionario  INT NOT NULL,
   nomeFuncionario  VARCHAR(80) NOT NULL,
   cpfFuncionario  VARCHAR(14) NOT NULL,
   rgFuncionario  VARCHAR(20) NOT NULL,
   dataNascimentoFuncionario  DATE NOT NULL,
   enderecoFuncionario  VARCHAR(255) NOT NULL,
   cepFuncionario  VARCHAR(15) NOT NULL,
   bairroFuncionario  VARCHAR(80) NOT NULL,
   cidadeFuncionario  VARCHAR(80) NOT NULL,
   estadoFuncionario  VARCHAR(2) NOT NULL,
   telefoneFuncionario  VARCHAR(16) NOT NULL,
   emailFuncionario  VARCHAR(80) NOT NULL,
   funcao  VARCHAR(40) NOT NULL,
   loginFuncionario  VARCHAR(45) NULL,
   senhaFuncionario  VARCHAR(15) NULL,
  PRIMARY KEY ( idFuncionario ));
 


-- -----------------------------------------------------
-- Table   Medico 
-- -----------------------------------------------------
CREATE TABLE   Medico  (
   idMedico  INT NOT NULL,
   nomeMedico  VARCHAR(80) NOT NULL,
   cnpj  VARCHAR(30) NOT NULL,
   crmEstado  VARCHAR(2) NOT NULL,
   crmNum  INT NOT NULL,
   especialidade  VARCHAR(30) NOT NULL,
   sala  INT NOT NULL,
   loginMedico  VARCHAR(20) NOT NULL,
   senhaMedico  VARCHAR(45) NOT NULL,
   dataDisponivel  JSON NOT NULL,
   horaDisponivelInicial  DATE NOT NULL,
   horaDisponivelFinal  DATE NOT NULL,
   valorConsulta  FLOAT NOT NULL,
  PRIMARY KEY ( idMedico ));
 


-- -----------------------------------------------------
-- Table   Paciente 
-- -----------------------------------------------------
CREATE TABLE   Paciente  (
   idPaciente  INT NOT NULL,
   nomeIdentidade  VARCHAR(80) NOT NULL,
   nomePreferencia  VARCHAR(80) NOT NULL,
   cpfPaciente  VARCHAR(14) NULL,
   passaportePaciente  VARCHAR(20) NULL,
   dataNascimentoPaciente  DATE NOT NULL,
   nomeResponsavel  VARCHAR(80) NULL,
   cpfResponsavel  VARCHAR(14) NULL,
   genero  VARCHAR(30) NULL,
   enderecoPaciente  VARCHAR(255) NOT NULL,
   cepPaciente  VARCHAR(15) NOT NULL,
   bairroPaciente  VARCHAR(45) NOT NULL,
   cidadePaciente  VARCHAR(80) NOT NULL,
   estadoPaciente  VARCHAR(2) NOT NULL,
   telefonePaciente  VARCHAR(16) NOT NULL,
   emailPaciente  VARCHAR(80) NOT NULL,
   planoSaude  VARCHAR(40) NOT NULL,
   numPlano  INT NULL,
   validadePlano  DATE NULL,
  PRIMARY KEY ( idPaciente ));
 


-- -----------------------------------------------------
-- Table   Consulta 
-- -----------------------------------------------------
CREATE TABLE   Consulta  (
   idConsulta  INT NOT NULL,
   idMedico  INT NOT NULL,
   idPaciente  INT NOT NULL,
   idFuncionario  INT NOT NULL,
   dataConsulta  DATE NOT NULL,
   horarioConsulta  DATE NOT NULL,
   retornoConsulta SMALLINT NOT NULL,
  PRIMARY KEY ( idConsulta ),
  CONSTRAINT  fk_Consulta_Medico 
    FOREIGN KEY ( idMedico )
    REFERENCES   Medico  ( idMedico ),
  CONSTRAINT  fk_Consulta_Paciente 
    FOREIGN KEY ( idPaciente )
    REFERENCES   Paciente  ( idPaciente ),
  CONSTRAINT  fk_Consulta_Funcionario 
    FOREIGN KEY ( idFuncionario )
    REFERENCES   Funcionario  ( idFuncionario ));