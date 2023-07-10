import moment from "moment";
import { useLocation, useNavigate } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/CadastroFuncionario.css";
import { ChangeEvent, useEffect, useState } from "react";
import { IFuncionario } from "../Models/IFuncionario";
import { Service } from "../Service";

export const CadastroFuncionario = () => {
  const location = useLocation();
  const [funcionario, setFuncionario] = useState<IFuncionario>();

  useEffect(() => {
    document.title = "Cadastro de Funcionário";
    setFuncionario(location.state);
  }, []);
  const onChange = (ev: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const objFuncionario = funcionario ?? {};

    const newValue = ev.target.value;
    const field = ev.target.name;

    const newObject = {
      ...objFuncionario,
      [field]: newValue,
    };

    setFuncionario(newObject);
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
      ...funcionario,
      [field]: newValue,
    };

    setFuncionario(newObject);
    // Use cpfWithMask as needed (e.g., update state or perform further operations)
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
      ...funcionario,
      [field]: newValue,
    };

    setFuncionario(newObject);
    // Use cpfWithMask as needed (e.g., update state or perform further operations)
  };

  const handleChangeCep = (event: React.ChangeEvent<HTMLInputElement>) => {
    const cep = event.target.value.replace(/\D/g, "");
    const cepWithMask = cep.replace(/(\d{5})(\d{3})/, "$1-$2");
    const newValue = cepWithMask;
    const field = event.target.name;

    const newObject = {
      ...funcionario,
      [field]: newValue,
    };

    setFuncionario(newObject);
    // Use cepWithMask as needed (e.g., update state or perform further operations)
  };

  const validate = () => {
    if (funcionario?.nome == "" || funcionario?.nome == null) {
      window.alert("O campo nome é obrigatório");
      return false;
    }

    if (funcionario?.cpf == "" || funcionario?.cpf == null) {
      window.alert("O campo cpf é obrigatório");
      return false;
    }

    if (funcionario?.rg == "" || funcionario?.rg == null) {
      window.alert("O campo rg é obrigatório");
      return false;
    }

    if (
      funcionario?.data_nascimento == "" ||
      funcionario?.data_nascimento == null
    ) {
      window.alert("O campo data de nascimento é obrigatório");
      return false;
    }

    if (funcionario?.endereco == "" || funcionario?.endereco == null) {
      window.alert("O campo endereço é obrigatório");
      return false;
    }

    if (funcionario?.cep == "" || funcionario?.cep == null) {
      window.alert("O campo cep é obrigatório");
      return false;
    }

    if (funcionario?.bairro == "" || funcionario?.bairro == null) {
      window.alert("O campo bairro é obrigatório");
      return false;
    }

    if (funcionario?.cidade == "" || funcionario?.cidade == null) {
      window.alert("O campo cidade é obrigatório");
      return false;
    }

    if (funcionario?.estado == "" || funcionario?.estado == null) {
      window.alert("O campo estado é obrigatório");
      return false;
    }

    if (funcionario?.telefone == "" || funcionario?.telefone == null) {
      window.alert("O campo telefone é obrigatório");
      return false;
    }

    if (funcionario?.email == "" || funcionario?.email == null) {
      window.alert("O campo email é obrigatório");
      return false;
    }

    if (funcionario?.funcao == "" || funcionario?.funcao == null) {
      window.alert("O campo função é obrigatório");
      return false;
    }

    if (funcionario?.id ?? 0 > 0) {
      return true;
    }

    return true;
  };
  const navigate = useNavigate();

  const registrar = () => {
    if (funcionario && validate()) {
      Service.getFuncionarios().then(() => {
        navigate("/Funcionarios");
      });
    }
    if (
      funcionario &&
      validate() &&
      window.confirm(
        "Deseja realmente cadastrar este Funcionário? " +
          JSON.stringify(funcionario)
      )
    ) {
      if (funcionario?.id ?? 0 > 0) {
        Service.PutFuncionarios({
          ...funcionario,
          data_nascimento: moment(funcionario.data_nascimento).format(
            "DD/MM/YYYY"
          ),
        })
          .then(() => {
            window.alert("Atualizado com sucesso");
            navigate("/Funcionarios");
          })
          .catch((err) =>
            window.alert("Erro:" + JSON.stringify(err?.response?.data))
          );
      } else {
        Service.PostFuncionarios({
          ...funcionario,
          data_nascimento: moment(funcionario.data_nascimento).format(
            "DD/MM/YYYY"
          ),
        })
          .then(() => {
            window.alert("Cadastrado com sucesso");
            navigate("/Funcionarios");
          })
          .catch((err) =>
            window.alert("Erro:" + JSON.stringify(err?.response?.data))
          );
      }
    }
  };

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
          <label htmlFor="inputNomel4" className="form-label">
            Nome completo
          </label>
          <input
            type="nome"
            value={funcionario?.nome}
            className="form-control"
            id="inputNome4"
            maxLength={80}
            minLength={10}
            onChange={onChange}
            name="nome"
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="inputCpf4" className="form-label">
            CPF
          </label>

          <input
            maxLength={14}
            minLength={14}
            className="CPF"
            onChange={handleChangeCPF}
            name="cpf"
            type="text"
            value={funcionario?.cpf}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputRg" className="form-label">
            RG
          </label>

          <input
            type="text"
            maxLength={20}
            minLength={5}
            name="rg"
            onChange={onChange}
            value={funcionario?.rg}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputDn" className="form-label">
            Data de Nascimento
          </label>

          <input
            type="date"
            name="data_nascimento"
            onChange={onChange}
            value={funcionario?.data_nascimento}
            //value={getTime(funcionario)}
          />
        </div>

        <div className="col-5">
          <label htmlFor="inputEmail" className="form-label">
            Email
          </label>
          <input
            maxLength={80}
            type="email"
            value={funcionario?.email}
            className="form-control"
            id="inputEmail"
            onChange={onChange}
            name="email"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputTel" className="form-label">
            Celular
          </label>
          <input
            maxLength={11}
            minLength={11}
            type="text"
            value={funcionario?.telefone}
            className="form-control"
            id="inputTel"
            name="telefone"
            onChange={handleChangeTel}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputFuncao" className="form-label">
            Função
          </label>
          <select
            id="inputFuncao"
            value={funcionario?.funcao}
            className="form-select"
            onChange={onChange}
            name="funcao"
          >
            <option value="">Selecionar...</option>
            <option value="ADMIN">Administrador</option>
            <option value="RECEPCIONISTA">Recepcionista</option>
            <option value="AUXILIAR">Auxiliar</option>
          </select>
        </div>

        <div className="col-3">
          <label htmlFor="inputLogin" className="form-label">
            Login
          </label>
          <input
            maxLength={30}
            type="text"
            value={funcionario?.login}
            className="form-control"
            id="inputLogin"
            onChange={onChange}
            name="login"
          />
        </div>

        <div className="col-2">
          <label htmlFor="inputSenha" className="form-label">
            Senha
          </label>
          <input
            maxLength={15}
            type="password"
            className="form-control"
            id="inputSenha"
            onChange={onChange}
            name="senha"
          />
        </div>

        <div className="col-7">
          <label htmlFor="inputAddress" className="form-label">
            Endereço
          </label>
          <input
            type="text"
            maxLength={255}
            onChange={onChange}
            name="endereco"
            className="form-control"
            value={funcionario?.endereco}
            id="inputAddress"
            placeholder="Logradouro, Número e Complemento"
          />
        </div>

        <div className="col-md-3">
          <label htmlFor="inputBairro" className="form-label">
            Bairro
          </label>
          <input
            maxLength={80}
            type="text"
            value={funcionario?.bairro}
            className="form-control"
            onChange={onChange}
            name="bairro"
            id="inputBairro"
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputCidade" className="form-label">
            Cidade
          </label>
          <input
            maxLength={80}
            type="text"
            value={funcionario?.cidade}
            className="form-control"
            id="inputCidade"
            onChange={onChange}
            name="cidade"
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="inputEstado" className="form-label">
            Estado
          </label>
          <select
            id="inputEstado"
            value={funcionario?.estado}
            className="form-select"
            onChange={onChange}
            name="estado"
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
            type="text"
            name="cep"
            value={funcionario?.cep}
            className="form-control"
            id="inputCep"
            onChange={handleChangeCep}
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
