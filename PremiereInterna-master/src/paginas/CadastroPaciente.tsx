//import { DadosPaciente } from "./Componentes/DadosPaciente";
import moment from "moment";
import { json, useLocation, useNavigate } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/CadastroPaciente.css";
import { ChangeEvent, useEffect, useState } from "react";
import { IPaciente } from "../Models/IPaciente";
import { Service } from "../Service";

export const CadastroPaciente = () => {
  const location = useLocation();
  const [paciente, setPaciente] = useState<IPaciente>();

  useEffect(() => {
    document.title = "Cadastro de Paciente";
    setPaciente(location.state);
  }, []);

  const onChange = (
    ev: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLSelectElement>
  ) => {
    const objPaciente = paciente ?? {};

    const newValue = ev.target.value;
    const field = ev.target.name;

    const newObject = {
      ...objPaciente,
      [field]: newValue,
    };

    setPaciente(newObject);
  };

  const handleChangeTel = (event: any) => {
    const Tel = event.target.value.replace(/\D/g, "");
    const telWithMask = Tel.replace(
      /(\d{2})(9)(\d{4})(\d{4})/,
      "($1) $2 $3-$4"
    );
    const newValue = telWithMask;
    const field = event.target.name;

    const newObject = {
      ...paciente,
      [field]: newValue,
    };

    setPaciente(newObject);
    // Use cpfWithMask as needed (e.g., update state or perform further operations)
  };

  const handleChangeCep = (event: React.ChangeEvent<HTMLInputElement>) => {
    const cep = event.target.value.replace(/\D/g, "");
    const cepWithMask = cep.replace(/(\d{5})(\d{3})/, "$1-$2");
    const newValue = cepWithMask;
    const field = event.target.name;

    const newObject = {
      ...paciente,
      [field]: newValue,
    };

    setPaciente(newObject);
    // Use cepWithMask as needed (e.g., update state or perform further operations)
  };

  const handleChangeCPF = (event: any) => {
    const cpf = event.target.value.replace(/\D/g, "");
    const cpfWithMask = cpf.replace(
      /(\d{3})(\d{3})(\d{3})(\d{2})/,
      "$1.$2.$3-$4"
    );
    const newValue = cpfWithMask;
    const field = event.target.name;

    const newObject = {
      ...paciente,
      [field]: newValue,
    };

    setPaciente(newObject);
    // Use cpfWithMask as needed (e.g., update state or perform further operations)
  };

  const validate = () => {
    if (paciente?.nome == "" || paciente?.nome == null) {
      window.alert("O campo nome é obrigatório");
      return false;
    }

    if (paciente?.data_nascimento == "" || paciente?.data_nascimento == null) {
      window.alert("O campo data de nascimento é obrigatório");
      return false;
    }

    if (paciente?.endereco == "" || paciente?.endereco == null) {
      window.alert("O campo endereço é obrigatório");
      return false;
    }

    if (paciente?.cep == "" || paciente?.cep == null) {
      window.alert("O campo cep é obrigatório");
      return false;
    }

    if (paciente?.bairro == "" || paciente?.bairro == null) {
      window.alert("O campo bairro é obrigatório");
      return false;
    }

    if (paciente?.cidade == "" || paciente?.cidade == null) {
      window.alert("O campo cidade é obrigatório");
      return false;
    }

    if (paciente?.estado == "" || paciente?.estado == null) {
      window.alert("O campo estado é obrigatório");
      return false;
    }

    if (paciente?.telefone == "" || paciente?.telefone == null) {
      window.alert("O campo telefone é obrigatório");
      return false;
    }

    if (paciente?.email == "" || paciente?.email == null) {
      window.alert("O campo email é obrigatório");
      return false;
    }

    if (paciente?.plano_saude == "" || paciente?.plano_saude == null) {
      window.alert("O campo plano de saude é obrigatório");
      return false;
    }

    if (paciente?.id ?? 0 > 0) {
      return true;
    }

    return true;
  };
  const navigate = useNavigate();

  const registrar = () => {
    if (paciente && validate()) {
      Service.getPacientes().then(() => {
        navigate("/Paciente");
      });
    }
    if (
      paciente &&
      validate() &&
      window.confirm(
        "Deseja realmente cadastrar este paciente? " + JSON.stringify(paciente)
      )
    ) {
      const formattedPaciente = {
        ...paciente,
        data_nascimento: moment(paciente.data_nascimento).format("DD/MM/YYYY"),
        validade_plano: moment(paciente.validade_plano).format("DD/MM/YYYY"),
      };
      if (paciente?.id ?? 0 > 0) {
        Service.PutPacientes(formattedPaciente)
          .then(() => {
            window.alert("Atualizado com sucesso");
            navigate("/Paciente");
          })
          .catch((err) =>
            window.alert("Erro:" + JSON.stringify(err?.response?.data))
          );
      } else {
        Service.PostPacientes(formattedPaciente)
          .then(() => {
            window.alert("Cadastrado com sucesso");
            navigate("/Paciente");
          })
          .catch((err) =>
            window.alert("Erro:" + JSON.stringify(err?.response?.data))
          );
      }
    }
  };

  return (
    <>
      <Cabecalho nomeTela="Dados Pacientes"></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Cadastro do Paciente</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-4">
          <label htmlFor="inputNomel4" className="form-label">
            Nome completo
          </label>
          <input
            maxLength={80}
            minLength={10}
            name="nome"
            type="text"
            value={paciente?.nome}
            className="form-control"
            id="inputNome4"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputCpf4" className="form-label">
            CPF
          </label>
          <input
            maxLength={14}
            minLength={14}
            name="cpf"
            type="text"
            value={paciente?.cpf}
            className="CPF"
            id="inputCpf4"
            onChange={handleChangeCPF}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputPassaporte" className="form-label">
            Passaporte
          </label>
          <input
            maxLength={20}
            name="passaporte"
            type="text"
            value={paciente?.passaporte}
            className="form-control"
            id="inputPassaporte"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputDn" className="form-label">
            Data de Nascimento
          </label>
          <input
            name="data_nascimento"
            type="date"
            value={paciente?.data_nascimento}
            className="form-control"
            id="inputDn"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputGenero" className="form-label">
            Genero
          </label>
          <input
            maxLength={30}
            name="genero"
            type="text"
            value={paciente?.genero}
            className="form-control"
            id="inputGenero"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputTel" className="form-label">
            Celular
          </label>
          <input
            maxLength={11}
            minLength={11}
            name="telefone"
            type="text"
            value={paciente?.telefone}
            className="form-control"
            id="inputTel"
            onChange={handleChangeTel}
          />
        </div>

        <div className="col-4">
          <label htmlFor="inputEmail" className="form-label">
            Email
          </label>
          <input
            maxLength={80}
            name="email"
            type="email"
            value={paciente?.email}
            className="form-control"
            id="inputEmail"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputNomeResponsavel" className="form-label">
            Nome Responsável
          </label>
          <input
            maxLength={80}
            minLength={10}
            name="nome_responsavel"
            type="text"
            value={paciente?.nome_responsavel}
            className="form-control"
            id="inputNomeResponsavel"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputCpfResponsavel" className="form-label">
            CPF Responsável
          </label>
          <input
            maxLength={14}
            minLength={14}
            name="cpf_responsavel"
            type="text"
            value={paciente?.cpf_responsavel}
            className="CPF"
            id="inputCpfResponsavel"
            onChange={handleChangeCPF}
          />
        </div>

        <div className="col-8">
          <label htmlFor="inputAddress" className="form-label">
            Endereço
          </label>
          <input
            maxLength={255}
            name="endereco"
            type="text"
            value={paciente?.endereco}
            className="form-control"
            id="inputAddress"
            placeholder="Logradouro, Número e Complemento"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputBairro" className="form-label">
            Bairro
          </label>
          <input
            maxLength={80}
            name="bairro"
            type="text"
            value={paciente?.bairro}
            className="form-control"
            id="inputBairro"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputCidade" className="form-label">
            Cidade
          </label>
          <input
            maxLength={80}
            name="cidade"
            type="text"
            value={paciente?.cidade}
            className="form-control"
            id="inputCidade"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="inputEstado" className="form-label">
            Estado
          </label>
          <select
            name="estado"
            id="inputEstado"
            value={paciente?.estado}
            className="form-select"
            onChange={onChange}
            onBlur={onChange}
          >
            <option value="">Selecionar...</option>
            <option>AC</option>
            <option>AL</option>
            <option>AP</option>
            <option>AM</option>
            <option>BA</option>
            <option>CE</option>
            <option>DF</option>
            <option>ES</option>
            <option>GO</option>
            <option>MA</option>
            <option>MT</option>
            <option>MS</option>
            <option>MG</option>
            <option>PA</option>
            <option>PB</option>
            <option>PR</option>
            <option>PE</option>
            <option>PI</option>
            <option>RJ</option>
            <option>RN</option>
            <option>RS</option>
            <option>RO</option>
            <option>RR</option>
            <option>SC</option>
            <option>SP</option>
            <option>SE</option>
            <option>TO</option>
          </select>
        </div>
        <div className="col-md-2">
          <label htmlFor="inputCep" className="form-label">
            CEP
          </label>
          <input
            maxLength={9}
            minLength={9}
            name="cep"
            type="text"
            value={paciente?.cep}
            className="form-control"
            id="inputCep"
            onChange={handleChangeCep}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputplanoSaude" className="form-label">
            Plano de Saúde
          </label>
          <select
            name="plano_saude"
            id="inputplanoSaude"
            value={paciente?.plano_saude}
            className="form-select"
            onChange={onChange}
            onBlur={onChange}
          >
            <option value="">Selecionar...</option>
            <option>São Camilo</option>
            <option>Unimed</option>
            <option>Bradesco Saúde</option>
            <option>Camed</option>
            <option>Famed</option>
            <option>Cassi</option>
            <option>Life</option>
            <option>Issec</option>
            <option>Particular</option>
          </select>
        </div>

        <div className="col-md-2">
          <label htmlFor="inputnumPlano" className="form-label">
            Número Plano
          </label>
          <input
            maxLength={30}
            name="num_plano"
            type="text"
            value={paciente?.num_plano}
            className="form-control"
            id="inputnumPlano"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputvalidadePlano" className="form-label">
            Validade do Plano
          </label>
          <input
            name="validade_plano"
            type="date"
            value={paciente?.validade_plano}
            className="date"
            id="inputvalidadePlano"
            onChange={onChange}
            onBlur={onChange}
          />
        </div>

        <div className="col-12">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={registrar}
            type="button"
          >
            Registrar
          </button>
        </div>
      </form>
    </>
  );
};
