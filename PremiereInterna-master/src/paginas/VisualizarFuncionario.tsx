import moment from "moment";
import {
  Navigate,
  useLocation,
  useNavigate,
  useParams,
} from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/CadastroFuncionario.css";
import { ChangeEvent, useEffect, useState } from "react";
import { IFuncionario } from "../Models/IFuncionario";
import { Service } from "../Service";
/*import axios from "axios";*/

export const VisualizarFuncionario = () => {
  const { nome } = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const [funcionario, setFuncionario] = useState<IFuncionario>();

  useEffect(() => {
    document.title = "Dados do Funcionário";
    Service.getFuncionariosPorNome(nome || "").then((result) => {
      setFuncionario(result.data[0]);
    });
  }, []);

  const getTime = (funcionario?: IFuncionario) => {
    if (!funcionario?.data_nascimento) return null;
    var parts = funcionario.data_nascimento.split("/");
    var formattedDate = parts.reverse().join("-");
    var date = new Date(formattedDate);

    var frs = date.toISOString().split("T")[0];

    return frs;
  };

  return (
    <>
      <Cabecalho nomeTela="Dados Funcionários"></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Cadastro do Funcionário</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-6">
          <label htmlFor=" input disabledNomel4" className="form-label">
            Nome completo
          </label>
          <input
            disabled
            type="nome"
            value={funcionario?.nome}
            className="form-control"
            id=" input disabledNome4"
            maxLength={80}
            minLength={10}
            name="nome"
          />
        </div>
        <div className="col-md-2">
          <label htmlFor=" input disabledCpf4" className="form-label">
            CPF
          </label>

          <input
            disabled
            maxLength={14}
            minLength={14}
            className="CPF"
            name="cpf"
            type="text"
            value={funcionario?.cpf}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor=" input disabledRg" className="form-label">
            RG
          </label>

          <input
            disabled
            type="text"
            maxLength={20}
            minLength={5}
            name="rg"
            value={funcionario?.rg}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor=" input disabledDn" className="form-label">
            Data de Nascimento
          </label>

          <input
            disabled
            type="text"
            name="data_nascimento"
            value={funcionario?.data_nascimento}
            //value={getTime(funcionario)}
          />
        </div>

        <div className="col-5">
          <label htmlFor=" input disabledEmail" className="form-label">
            Email
          </label>
          <input
            disabled
            maxLength={80}
            type="email"
            value={funcionario?.email}
            className="form-control"
            id=" input disabledEmail"
            name="email"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor=" input disabledTel" className="form-label">
            Celular
          </label>
          <input
            disabled
            maxLength={11}
            minLength={11}
            type="text"
            value={funcionario?.telefone}
            className="form-control"
            id=" input disabledTel"
            name="telefone"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor=" input disabledFuncao" className="form-label">
            Função
          </label>
          <select
            id=" input disabledFuncao"
            value={funcionario?.funcao}
            className="form-select"
            name="funcao"
          >
            <option selected>Selecionar...</option>

            <option>ADMIN</option>
            <option>RECEPCIONISTA</option>
            <option>AUXILIAR</option>
          </select>
        </div>

        <div className="col-3">
          <label htmlFor=" input disabledLogin" className="form-label">
            Login
          </label>
          <input
            disabled
            maxLength={30}
            type="text"
            value={funcionario?.login}
            className="form-control"
            id=" input disabledLogin"
            name="login"
          />
        </div>

        <div className="col-2">
          <label htmlFor=" input disabledSenha" className="form-label">
            Senha
          </label>
          <input
            disabled
            maxLength={15}
            type="password"
            className="form-control"
            id=" input disabledSenha"
            name="senha"
          />
        </div>

        <div className="col-7">
          <label htmlFor=" input disabledAddress" className="form-label">
            Endereço
          </label>
          <input
            disabled
            type="text"
            maxLength={255}
            name="endereco"
            className="form-control"
            value={funcionario?.endereco}
            id=" input disabledAddress"
            placeholder="Logradouro, Número e Complemento"
          />
        </div>

        <div className="col-md-3">
          <label htmlFor=" input disabledBairro" className="form-label">
            Bairro
          </label>
          <input
            disabled
            maxLength={80}
            type="text"
            value={funcionario?.bairro}
            className="form-control"
            name="bairro"
            id=" input disabledBairro"
          />
        </div>

        <div className="col-md-4">
          <label htmlFor=" input disabledCidade" className="form-label">
            Cidade
          </label>
          <input
            disabled
            maxLength={80}
            type="text"
            value={funcionario?.cidade}
            className="form-control"
            id=" input disabledCidade"
            name="cidade"
          />
        </div>
        <div className="col-md-2">
          <label htmlFor=" input disabledEstado" className="form-label">
            Estado
          </label>
          <select
            id=" input disabledEstado"
            value={funcionario?.estado}
            className="form-select"
            name="estado"
          >
            <option selected>Selecionar...</option>
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
          <label htmlFor=" input disabledCep" className="form-label">
            CEP
          </label>
          <input
            disabled
            maxLength={9}
            minLength={9}
            type="text"
            name="cep"
            value={funcionario?.cep}
            className="form-control"
            id=" input disabledCep"
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
