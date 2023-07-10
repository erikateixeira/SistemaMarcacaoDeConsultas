import { Cabecalho } from "./Componentes/Cabecalho";
import { useEffect, useState, ChangeEvent } from "react";
import { IConsulta } from "../Models/IConsulta";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Service } from "../Service";
import { useConfirmacao } from "../ConfirmacaoContext";

type ConsultaConfirmacao = {
  id_consulta: number;
  nome_medico: string;
  especialidade: string;
  data_consulta: any;
  hora_consulta: any;
  retorno_consulta: string;
  nome_paciente: string;
  telefone_paciente: string;
  valor_consulta: number;
  plano_saude: string;
  nome_funcionario: string;
  confirmacao: any;
};

export const VisualizarConsulta = () => {
  const navigate = useNavigate();
  const { id_consulta } = useParams();
  const [ControleConsulta, setControleConsulta] =
    useState<ConsultaConfirmacao>();
  const { setConfirmacao, setConfirmacaoIdConsulta } = useConfirmacao();

  const confirmar = (id_consulta?: number) => {
    if (window.confirm("Deseja realmente confirmar esta consulta?")) {
      Service.PutConsultaConfirmacao(id_consulta, true)
        .then(() => {
          window.alert("Consulta confirmada com sucesso");
          setConfirmacao(true);
          setConfirmacaoIdConsulta(Number(id_consulta));
          navigate(-1);
        })
        .catch((err) =>
          window.alert("Erro:" + JSON.stringify(err?.response?.data))
        );
    }
  };

  const encaminharParaRemarcarConsulta = (infoConsulta?: IConsulta) => {
    return navigate("/RemarcarConsulta/" + id_consulta);
  };

  const apagar = (id_consulta?: Number) => {
    if (window.confirm("Deseja realmente apagar esta consulta?")) {
      Service.deleteConsulta(id_consulta)
        .then(() => {
          window.alert("Excluido com sucesso");
          navigate(-1);
        })
        .catch((err) =>
          window.alert("Erro:" + JSON.stringify(err?.response?.data))
        );
    }
  };

  useEffect(() => {
    document.title = "Dados da Consulta";
    Service.getConsultaConfirmacao(Number(id_consulta)).then((result) => {
      setControleConsulta(result.data);
    });
  }, []);

  return (
    <>
      <Cabecalho nomeTela=""></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Dados Consulta</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-4">
          <label htmlFor="inputDataConsulta" className="form-label">
            Data da Consulta
          </label>
          <input
            type="text"
            name="data_consulta"
            value={ControleConsulta?.data_consulta}
            className="form-control"
            id="inputDataConsulta"
            disabled
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputHoraConsulta" className="form-label">
            Hora da Consulta
          </label>
          <input
            type="text"
            name="hora_consulta"
            value={ControleConsulta?.hora_consulta}
            className="form-control"
            id="inputHoraConsulta"
            disabled
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputRetornoConsulta" className="form-label">
            Será retorno?
          </label>
          <input
            type="text"
            name="retorno_consulta"
            value={ControleConsulta?.retorno_consulta}
            className="form-control"
            id="inputRetornoConsulta"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputNomeMedico" className="form-label">
            Nome do Médico
          </label>
          <input
            type="text"
            name="nome_medico"
            value={ControleConsulta?.nome_medico}
            className="form-control"
            id="inputNomeMedico"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputEspecialidade" className="form-label">
            Especialidade
          </label>
          <input
            type="text"
            name="especialidade"
            value={ControleConsulta?.especialidade}
            className="form-control"
            id="inputEspecialidade"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputNomePaciente" className="form-label">
            Nome do Paciente
          </label>
          <input
            type="text"
            name="nome_paciente"
            value={ControleConsulta?.nome_paciente}
            className="form-control"
            id="inputNomePaciente"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputTelefone" className="form-label">
            Telefone
          </label>
          <input
            type="text"
            name="telefone"
            value={ControleConsulta?.telefone_paciente}
            className="form-control"
            id="inputTelefone"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputPS" className="form-label">
            Plano de Saúde
          </label>
          <input
            type="text"
            name="plano_saude"
            value={ControleConsulta?.plano_saude}
            className="form-control"
            id="inputPS"
            disabled
          />
        </div>

        <div className="col-md-6">
          <label htmlFor="inputValor" className="form-label">
            Valor da consulta
          </label>
          <input
            type="number"
            name="valor_consulta"
            value={ControleConsulta?.valor_consulta}
            className="form-control"
            id="inputValor"
            disabled
          />
        </div>

        <div className="col-4">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={() => confirmar(ControleConsulta?.id_consulta)}
            type="button"
          >
            Confirmar
          </button>
        </div>

        <div className="col-4">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={() => encaminharParaRemarcarConsulta()}
          >
            {" "}
            Remarcar
          </button>
        </div>

        <div className="col-4">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={() => apagar(ControleConsulta?.id_consulta)}
            type="button"
          >
            Cancelar
          </button>
        </div>

        <div className="col-4">
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
