import { DadosConsulta } from "./Componentes/DadosConsulta";
import { CadastroPaciente } from "../paginas/CadastroPaciente";
import { Cabecalho } from "./Componentes/Cabecalho";
import { useEffect, useState, ChangeEvent } from "react";
import { IConsulta } from "../Models/IConsulta";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Service } from "../Service";
import { IFuncionario } from "../Models/IFuncionario";
import { IPaciente } from "../Models/IPaciente";
import { IMedico } from "../Models/IMedico";
type ConsultaAtendimento = {
  id_consulta: number;
  nome_medico: string;
  especialidade: string;
  sala: string;
  data_consulta: any;
  hora_consulta: any;
  retorno_consulta: string;
  nome_paciente: string;
  plano_saude: string;
  num_plano: string;
  validade_plano: any;
  valor_consulta: number;
  nome_funcionario: string;
};

export const AbrirAtendimento = () => {
  const navigate = useNavigate();
  const { id_consulta } = useParams();
  const [ControleConsulta, setControleConsulta] =
    useState<ConsultaAtendimento>();

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

  const encaminharParaConfirmarAtendimento = () => {
    if (
      window.confirm(
        "O médico será informado que o paciente está disponível para o atendimento. Você confirma?"
      )
    )
      return navigate(-1);
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
    Service.getConsultaAtendimento(Number(id_consulta)).then((result) => {
      setControleConsulta(result.data);
    });
  }, []);

  return (
    <>
      <Cabecalho nomeTela=""></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Abrir Atendimento</h1>
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

        <div className="col-md-4">
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

        <div className="col-md-4">
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

        <div className="col-md-4">
          <label htmlFor="inputSala" className="form-label">
            Sala
          </label>
          <input
            type="text"
            name="sala"
            value={ControleConsulta?.sala}
            className="form-control"
            id="inputSala"
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

        <div className="col-md-4">
          <label htmlFor="inputNumPlano" className="form-label">
            Número do plano de saúde
          </label>
          <input
            type="text"
            name="num_plano"
            value={ControleConsulta?.num_plano}
            className="form-control"
            id="inputNumPlano"
            disabled
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputValPlano" className="form-label">
            Validade do plano de saúde
          </label>
          <input
            type="text"
            name="validade_plano"
            value={ControleConsulta?.validade_plano}
            className="form-control"
            id="inputValPlano"
            disabled
          />
        </div>

        <div className="col-md-4">
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
            onClick={() => encaminharParaConfirmarAtendimento()}
            type="button"
          >
            Autorização do Plano
          </button>
        </div>

        <div className="col-4">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={() => encaminharParaConfirmarAtendimento()}
            type="button"
          >
            Pagamento Realizado
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
      </form>
    </>
  );
};
