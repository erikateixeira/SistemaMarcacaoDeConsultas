import { Cabecalho } from "./Componentes/Cabecalho";
import { useEffect, useState, ChangeEvent } from "react";
import { IConsulta } from "../Models/IConsulta";
import { useLocation, useNavigate } from "react-router-dom";
import { Service } from "../Service";
import { IFuncionario } from "../Models/IFuncionario";
import { IPaciente } from "../Models/IPaciente";
import { IMedico } from "../Models/IMedico";

export const CriarConsulta = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [ControleConsulta, setControleConsulta] = useState<IConsulta>();
  const [medico, setMedico] = useState<IMedico>();
  const [paciente, setPaciente] = useState<IPaciente>();
  const [funcionario, setFuncionario] = useState<IFuncionario>();
  const [especialidade, setEspecialidade] = useState("");
  const [medicoSelecionado, setMedicoSelecionado] = useState("");
  const [datasDisponiveis, setDatasDisponiveis] = useState([]);
  const [dataSelecionada, setDataSelecionada] = useState("");
  const [horasDisponiveis, setHorasDisponiveis] = useState([]);
  const [horaSelecionada, setHoraSelecionada] = useState("");
  const [nomePesquisado, setNomePesquisado] = useState("");
  const [nomePesquisado2, setNomePesquisado2] = useState("");
  const [listaPaciente, setListaPacientes] = useState<IPaciente[]>([]);
  const [listaFuncionario, setListaFuncionarios] = useState<IFuncionario[]>([]);
  const [retorno, setRetorno] = useState("");
  const [medicosDisponiveis, setMedicosDisponiveis] = useState<string[]>([]);

  useEffect(() => {
    setControleConsulta(location.state);
  }, []);

  useEffect(() => {
    Service.getPacientes().then((res) => {
      setListaPacientes(res.data);
    });
  }, []);

  useEffect(() => {
    Service.getFuncionarios().then((res) => {
      setListaFuncionarios(res.data);
    });
  }, []);

  const pacienteNome = paciente ? paciente.nome : "";
  const funcionarioNome = funcionario ? funcionario.nome : "";

  /*const onChange = (ev: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const objConsulta = ControleConsulta ?? {};
    const newValue = ev.target.value;
    const field = ev.target.name;

    const newObject = {
      ...objConsulta,
      [field]: newValue,
    };

    setControleConsulta(newObject);
  };*/

  const onChangeEspecialidade = (event: { target: { value: any } }) => {
    const selectedEspecialidade = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      especialidade: selectedEspecialidade,
    });
    setEspecialidade(selectedEspecialidade);
    Service.getMedicosPorEspecialidade(selectedEspecialidade)
      .then((response) => {
        setMedicosDisponiveis(response.data);
      })
      .catch((error) => {
        console.log("Erro ao obter médicos por especialidade:", error);
      });
  };

  const onChangeMedico = (event: { target: { value: any } }) => {
    const selectedMedico = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      nome_medico: selectedMedico,
    });
    setMedicoSelecionado(selectedMedico);

    Service.getDatasDisponiveisPorMedico(selectedMedico)
      .then((response) => {
        setDatasDisponiveis(response.data);
      })
      .catch((error) => {
        console.log("Erro ao obter datas disponíveis:", error);
      });
  };

  const onChangeData = (event: { target: { value: any } }) => {
    const selectedData = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      data_consulta: selectedData,
    });
    setDataSelecionada(selectedData);

    setHorasDisponiveis([]); // Limpa as horas disponíveis antes de fazer a chamada

    Service.getHorasValidas(medicoSelecionado, selectedData)
      .then((response) => {
        const horasValidas = response.data;
        setHorasDisponiveis(horasValidas); // Atualiza as horas disponíveis com a resposta da API
      })
      .catch((error) => {
        console.log("Erro ao obter horas válidas:", error);
      });
  };

  const onChangeHora = (event: { target: { value: any } }) => {
    const selectedHora = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      hora_consulta: selectedHora,
    });
    setHoraSelecionada(selectedHora);
  };

  const onChangeNomePesquisado = (event: { target: { value: any } }) => {
    const nome = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      nome_paciente: nome,
    });
    setNomePesquisado(nome);
  };

  const onChangeNomePesquisado2 = (event: { target: { value: any } }) => {
    const nome2 = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      nome_funcionario: nome2,
    });
    setNomePesquisado2(nome2);
  };

  const onChangeRetorno = (event: { target: { value: any } }) => {
    const retorno = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      retorno_consulta: retorno,
    });
    setRetorno(retorno);
  };

  const validate = () => {
    if (
      ControleConsulta?.especialidade == "" ||
      ControleConsulta?.especialidade == null
    ) {
      window.alert("O campo especialidade é obrigatório");
      return false;
    }
    if (
      ControleConsulta?.nome_medico == "" ||
      ControleConsulta?.nome_medico == null
    ) {
      window.alert("O campo nome do médico é obrigatório");
      return false;
    }
    if (
      ControleConsulta?.nome_paciente == "" ||
      ControleConsulta?.nome_paciente == null
    ) {
      window.alert("O campo nome do paciente é obrigatório");
      return false;
    }

    if (
      ControleConsulta?.nome_funcionario == "" ||
      ControleConsulta?.nome_funcionario == null
    ) {
      window.alert("O campo nome do funcionário é obrigatório");
      return false;
    }

    if (ControleConsulta?.data_consulta == null) {
      window.alert("O campo data de consulta é obrigatório");
      return false;
    }

    if (
      ControleConsulta?.hora_consulta == "" ||
      ControleConsulta?.hora_consulta == null
    ) {
      window.alert("O campo hora é obrigatório");
      return false;
    }

    if (
      ControleConsulta?.retorno_consulta == "" ||
      ControleConsulta?.retorno_consulta == null
    ) {
      window.alert("O campo retorno consulta é obrigatório");
      return false;
    }

    if (ControleConsulta?.id_consulta ?? 0 > 0) {
      return true;
    }

    return true;
  };

  const registrar = () => {
    const consultar = {
      especialidade: especialidade,
      nome_medico: medicoSelecionado,
      data_consulta: dataSelecionada,
      hora_consulta: horaSelecionada,
      retorno_consulta: retorno,
      nome_paciente: nomePesquisado,
      nome_funcionario: nomePesquisado2,
      id_consulta: ControleConsulta?.id_consulta,
    };
    setControleConsulta(consultar);
    if (
      consultar &&
      validate() &&
      window.confirm(
        "Deseja realmente cadastrar esta consulta? " + JSON.stringify(consultar)
      )
    ) {
      if (consultar.id_consulta ?? 0 > 0) {
        Service.PutConsulta(consultar)
          .then(() => {
            window.alert("Atualizado com sucesso");
            navigate("/ControleConsulta");
          })
          .catch((err) =>
            window.alert("Erro:" + JSON.stringify(err?.response?.data))
          );
      } else {
        Service.PostConsulta(consultar)
          .then(() => {
            window.alert("Cadastrado com sucesso");
            navigate("/ControleConsulta");
          })
          .catch((err) =>
            window.alert("Erro:" + JSON.stringify(err?.response?.data))
          );
      }
    }
  };

  return (
    <>
      <Cabecalho nomeTela=""></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Criar Consulta</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-6">
          <label htmlFor="inputEspecialidade" className="form-label">
            Especialidade
          </label>
          <select
            name="especialidade"
            value={especialidade}
            className="form-control"
            id="inputEspecialidade"
            onChange={onChangeEspecialidade}
          >
            <option value="">Selecionar...</option>
            <option value="cardiologista">Cardiologista</option>
            <option value="dermatologista">Dermatologista</option>
            <option value="ginecologista">Ginecologista</option>
          </select>
        </div>

        <div className="col-md-6">
          <label htmlFor="inputMedico" className="form-label">
            Médico
          </label>
          <select
            name="medico"
            value={medicoSelecionado}
            className="form-control"
            id="inputMedico"
            onChange={onChangeMedico}
          >
            <option value="">Selecionar...</option>
            {medicosDisponiveis.map((Medico) => (
              <option key={Medico}>{Medico ?? ""}</option>
            ))}
          </select>
        </div>

        <div className="col-md-4">
          <label htmlFor="inputData" className="form-label">
            Data da consulta
          </label>
          <select
            name="data"
            value={dataSelecionada}
            className="form-control"
            id="inputData"
            onChange={onChangeData}
          >
            <option value="">Selecionar...</option>
            {datasDisponiveis.map((data) => (
              <option key={data} value={data}>
                {data}
              </option>
            ))}
          </select>
        </div>

        <div className="col-md-4">
          <label htmlFor="inputHoras" className="form-label">
            Horas disponíveis
          </label>
          <select
            name="horas"
            value={horaSelecionada}
            className="form-control"
            id="inputHoras"
            onChange={onChangeHora}
          >
            <option value="">Selecionar...</option>
            {horasDisponiveis.map((hora) => (
              <option key={hora} value={hora}>
                {hora}
              </option>
            ))}
          </select>
        </div>

        <div className="col-md-4">
          <label htmlFor="inputRetorno" className="form-label">
            Será retorno?
          </label>
          <select
            id="inputRetorno"
            value={retorno}
            className="form-select"
            onChange={onChangeRetorno}
            name="retorno"
          >
            <option value="">Selecionar...</option>
            <option value="true">SIM</option>
            <option value="false">NÃO</option>
          </select>
        </div>

        <div className="col-md-6">
          <label htmlFor="inputNome" className="form-label">
            Nome do paciente
          </label>
          <select
            name="nome_paciente"
            className="form-control"
            id="inputNomePac"
            onChange={onChangeNomePesquisado}
          >
            {" "}
            <option> Selecione</option>
            {listaPaciente.map((paciente) => (
              <option key={paciente.id}>{paciente?.nome ?? ""}</option>
            ))}
          </select>
          <p>{pacienteNome}</p>
        </div>

        <div className="col-md-6">
          <label htmlFor="inputNome" className="form-label">
            Nome do funcionário
          </label>
          <select
            name="nome_funcionario"
            className="form-control"
            id="inputNomeFunc"
            onChange={onChangeNomePesquisado2}
          >
            <option> Selecione</option>
            {listaFuncionario.map((funcionario) => (
              <option key={funcionario.id}>{funcionario?.nome ?? ""}</option>
            ))}
          </select>
          <p>{funcionarioNome}</p>
        </div>

        <div className="col-12">
          <button
            type="button"
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={registrar}
          >
            Registrar
          </button>
        </div>
      </form>
    </>
  );
};
