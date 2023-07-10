//import { DadosPaciente } from "./Componentes/DadosPaciente";
import moment from "moment";
import { json, useLocation, useNavigate, useParams } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/CadastroPaciente.css";
import { ChangeEvent, useEffect, useState } from "react";
import { IPaciente } from "../Models/IPaciente";
import { Service } from "../Service";

export const VisualizarPaciente = () => {
  const { nome } = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const [paciente, setPaciente] = useState<IPaciente>();

  useEffect(() => {
    document.title = "Dados de Paciente";
    Service.getPacientesPorNome(nome || "").then((result) => {
      setPaciente(result.data[0]);
    });
  }, []);

  return (
    <>
      <Cabecalho nomeTela="Dados Pacientes"></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Cadastro do Paciente</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-4">
          <label htmlFor="input disabledNomel4" className="form-label">
            Nome completo
          </label>
          <input
            disabled
            maxLength={80}
            minLength={10}
            name="nome"
            type="text"
            value={paciente?.nome}
            className="form-control"
            id="input disabledNome4"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledCpf4" className="form-label">
            CPF
          </label>
          <input
            disabled
            maxLength={14}
            minLength={14}
            name="cpf"
            type="text"
            value={paciente?.cpf}
            className="CPF"
            id="input disabledCpf4"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledPassaporte" className="form-label">
            Passaporte
          </label>
          <input
            disabled
            maxLength={20}
            name="passaporte"
            type="text"
            value={paciente?.passaporte}
            className="form-control"
            id="input disabledPassaporte"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledDn" className="form-label">
            Data de Nascimento
          </label>
          <input
            disabled
            name="data_nascimento"
            type="text"
            value={paciente?.data_nascimento}
            className="form-control"
            id="input disabledDn"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledGenero" className="form-label">
            Genero
          </label>
          <input
            disabled
            maxLength={30}
            name="genero"
            type="text"
            value={paciente?.genero}
            className="form-control"
            id="input disabledGenero"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledTel" className="form-label">
            Celular
          </label>
          <input
            disabled
            maxLength={11}
            minLength={11}
            name="telefone"
            type="text"
            value={paciente?.telefone}
            className="form-control"
            id="input disabledTel"
          />
        </div>

        <div className="col-4">
          <label htmlFor="input disabledEmail" className="form-label">
            Email
          </label>
          <input
            disabled
            maxLength={80}
            name="email"
            type="email"
            value={paciente?.email}
            className="form-control"
            id="input disabledEmail"
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="input disabledNomeResponsavel" className="form-label">
            Nome Responsável
          </label>
          <input
            disabled
            maxLength={80}
            minLength={10}
            name="nome_responsavel"
            type="text"
            value={paciente?.nome_responsavel}
            className="form-control"
            id="input disabledNomeResponsavel"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledCpfResponsavel" className="form-label">
            CPF Responsável
          </label>
          <input
            disabled
            maxLength={14}
            minLength={14}
            name="cpf_responsavel"
            type="text"
            value={paciente?.cpf_responsavel}
            className="CPF"
            id="input disabledCpfResponsavel"
          />
        </div>

        <div className="col-8">
          <label htmlFor="input disabledAddress" className="form-label">
            Endereço
          </label>
          <input
            disabled
            maxLength={255}
            name="endereco"
            type="text"
            value={paciente?.endereco}
            className="form-control"
            id="input disabledAddress"
            placeholder="Logradouro, Número e Complemento"
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="input disabledBairro" className="form-label">
            Bairro
          </label>
          <input
            disabled
            maxLength={80}
            name="bairro"
            type="text"
            value={paciente?.bairro}
            className="form-control"
            id="input disabledBairro"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledCidade" className="form-label">
            Cidade
          </label>
          <input
            disabled
            maxLength={80}
            name="cidade"
            type="text"
            value={paciente?.cidade}
            className="form-control"
            id="input disabledCidade"
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="input disabledEstado" className="form-label">
            Estado
          </label>
          <select
            name="estado"
            id="input disabledEstado"
            value={paciente?.estado}
            className="form-select"
          >
            <option defaultValue="">Selecionar...</option>
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
          <label htmlFor="input disabledCep" className="form-label">
            CEP
          </label>
          <input
            disabled
            maxLength={9}
            minLength={9}
            name="cep"
            type="text"
            value={paciente?.cep}
            className="form-control"
            id="input disabledCep"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledplanoSaude" className="form-label">
            Plano de Saúde
          </label>
          <select
            name="plano_saude"
            id="input disabledplanoSaude"
            value={paciente?.plano_saude}
            className="form-select"
          >
            <option defaultValue="">Selecionar...</option>
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
          <label htmlFor="input disablednumPlano" className="form-label">
            Número Plano
          </label>
          <input
            disabled
            maxLength={30}
            name="num_plano"
            type="text"
            value={paciente?.num_plano}
            className="form-control"
            id="input disablednumPlano"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledvalidadePlano" className="form-label">
            Validade do Plano
          </label>
          <input
            disabled
            name="validade_plano"
            type="text"
            value={paciente?.validade_plano}
            className="date"
            id="input disabledvalidadePlano"
          />
        </div>

        <div className="col-12">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={() => navigate(-1)}
            type="button"
          >
            Voltar
          </button>
        </div>
      </form>
    </>
  );
};
