import { Cabecalho } from "./Componentes/Cabecalho";
import { useEffect, useState, ChangeEvent } from "react";
import { IConsulta } from "../Models/IConsulta";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Service } from "../Service";
import { IFuncionario } from "../Models/IFuncionario";
import { IPaciente } from "../Models/IPaciente";
import { IMedico } from "../Models/IMedico";
type Remarcacao = {
  id_consulta: number;
  nome_medico: string;
  especialidade: string;
  data_consulta: any;
  hora_consulta: any;
  retorno_consulta: string;
  nome_paciente: string;
  nome_funcionario: string;
  confirmacao: any;
  autorizacao: any;
  pagamento: any;
};

export const RemarcarConsulta = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id_consulta } = useParams();
  const [ControleConsulta, setControleConsulta] = useState<IConsulta>();
  const [RemarcaConsulta, setRemarcaConsulta] = useState<Remarcacao>();
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
    document.title = "Dados da Consulta";
    Service.getConsultaConfirmacao(Number(id_consulta)).then((result) => {
      setRemarcaConsulta(result.data);
    });
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

  useEffect(() => {
    Service.getDatasDisponiveisPorMedico(RemarcaConsulta?.nome_medico || "")
      .then((response) => {
        setDatasDisponiveis(response.data);
      })
      .catch((error) => {
        console.log("Erro ao obter datas disponíveis:", error);
      });
  }, [RemarcaConsulta?.nome_medico]);

  const funcionarioNome = funcionario ? funcionario.nome : "";

  const onChangeMedico = (event: { target: { value: any } }) => {
    Service.getDatasDisponiveisPorMedico(RemarcaConsulta?.nome_medico || "")
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

    Service.getHorasValidas(RemarcaConsulta?.nome_medico || "", selectedData)
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

  const onChangeNomePesquisado2 = (event: { target: { value: any } }) => {
    const nome2 = event.target.value;
    setControleConsulta({
      ...ControleConsulta,
      nome_funcionario: nome2,
    });
    setNomePesquisado2(nome2);
  };

  const validate = () => {
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

    if (ControleConsulta?.id_consulta ?? 0 > 0) {
      return true;
    }

    return true;
  };

  const registrar = () => {
    const consultar = {
      data_consulta: dataSelecionada,
      hora_consulta: horaSelecionada,
      nome_funcionario: nomePesquisado2,
      id_consulta: RemarcaConsulta?.id_consulta,
      especialidade: RemarcaConsulta?.especialidade,
      nome_medico: RemarcaConsulta?.nome_medico,
      nome_paciente: RemarcaConsulta?.nome_paciente,
      retorno_consulta: RemarcaConsulta?.retorno_consulta,
    };
    setControleConsulta(consultar);
    if (
      consultar &&
      validate() &&
      window.confirm(
        "Deseja realmente remarcar esta consulta? " + JSON.stringify(consultar)
      )
    ) {
      if (RemarcaConsulta?.id_consulta ?? 0 > 0) {
        Service.PutConsulta(consultar)
          .then(() => {
            window.alert("Atualizado com sucesso");
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
        <h1>Remarcar Consulta</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-6">
          <label htmlFor="inputEspecialidade" className="form-label">
            Especialidade
          </label>
          <input
            type="text"
            name="especialidade"
            value={RemarcaConsulta?.especialidade}
            className="form-control"
            id="inputEspecialidade"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputMedico" className="form-label">
            Médico
          </label>
          <input
            type="text"
            name="nome_medico"
            value={RemarcaConsulta?.nome_medico}
            className="form-control"
            id="inputNomeMedico"
            disabled
          />
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
          <input
            type="text"
            name="retorno_consulta"
            value={RemarcaConsulta?.retorno_consulta}
            className="form-control"
            id="inputRetornoConsulta"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputNome" className="form-label">
            Nome do paciente
          </label>
          <input
            type="text"
            name="nome_paciente"
            value={RemarcaConsulta?.nome_paciente}
            className="form-control"
            id="inputNomePaciente"
            disabled
          />
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
