import { ChangeEvent, useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Cabecalho } from "./Cabecalho";
import { IMedico } from "../../Models/IMedico";
import { Service } from "../../Service";
import { IPaciente } from "../../Models/IPaciente";
import { IConsulta } from "../../Models/IConsulta";
import { Paciente } from "../Paciente";
import { IFuncionario } from "../../Models/IFuncionario";
import Funcionarios from "../Funcionarios";
import { Medico } from "../Medico";

type IniciarAtendimento = {
  id_consulta: number;
  nome_medico: string;
  crm_estado: string;
  crm_num: string;
  especialidade: string;
  data_consulta: any;
  hora_consulta: any;
  nome_paciente: string;
  cpf: string;
  passaporte: string;
  data_nascimento: any;
  telefone: string;
  email: string;
};

export const DadosConsulta = () => {
  const { id_consulta } = useParams();
  const [ControleConsulta, setControleConsulta] =
    useState<IniciarAtendimento>();

  useEffect(() => {
    document.title = "Dados da Consulta";
    Service.getIniciar(Number(id_consulta)).then((result) => {
      setControleConsulta(result.data);
    });
  }, []);

  /*useEffect(() => {
    document.title = "Dados da Consulta";
    console.log(id_consulta);
    if (id_consulta) {
      Service.getIniciar(Number(id_consulta))
        .then((result) => {
          setControleConsulta(result.data);
        })
        .catch((error) => {
          console.log("Erro ao obter os dados da consulta:", error);
        });
    }
  }, [id_consulta]);*/

  /*interface IPropriedades {
  consulta?: IConsulta;
  onConsultaChange?: (consulta: IConsulta) => void;
}

export const DadosConsulta: React.FC<IPropriedades> = ({
  consulta,
  onConsultaChange,
}) => {
  const [listaMedicos, setListaMedicos] = useState<IMedico[]>([]);
  const [listaPacientes, setListaPacientes] = useState<IPaciente[]>([]);
  const [listaFuncionarios, setListaFuncionarios] = useState<IFuncionario[]>(
    []
  );
  const handleChange = (ev: ChangeEvent<HTMLInputElement>) => {
    const objConsulta = consulta ?? {};

    const newValue = ev.target.checked;
    const field = ev.target.name;

    const newObject = {
      ...objConsulta,
      [field]: newValue,
    };

    if (onConsultaChange) onConsultaChange(newObject);
  };

  useEffect(() => {
    Service.getMedicos().then((res) => {
      setListaMedicos(res.data);
    });

    Service.getPacientes().then((res) => {
      setListaPacientes(res.data);
    });

    Service.getFuncionarios().then((res) => {
      setListaFuncionarios(res.data);
    });
  }, []);

  const onChange = (
    ev: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLSelectElement>
  ) => {
    const objConsulta = consulta ?? {};

    const newValue = ev.target.value;
    const field = ev.target.name;

    const newObject = {
      ...objConsulta,
      [field]: newValue,
    };

    if (onConsultaChange) onConsultaChange(newObject);
  };*/

  return (
    <>
      <form className="row g-3">
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

        <div className="col-md-3">
          <label htmlFor="inputCpfPaciente" className="form-label">
            CPF
          </label>
          <input
            type="text"
            name="cpf_paciente"
            value={ControleConsulta?.cpf}
            className="form-control"
            id="inputCpfPaciente"
            disabled
          />
        </div>

        <div className="col-md-3">
          <label htmlFor="inputPassaporte" className="form-label">
            Passaporte
          </label>
          <input
            type="text"
            name="passaporte_paciente"
            value={ControleConsulta?.passaporte}
            className="form-control"
            id="inputPassaporte"
            disabled
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputDataNascimento" className="form-label">
            Data de Nascimento
          </label>
          <input
            type="text"
            name="data_nascimento"
            value={ControleConsulta?.data_nascimento}
            className="form-control"
            id="inputDataNascimento"
            disabled
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputTelefone" className="form-label">
            Telefone
          </label>
          <input
            type="text"
            name="telefone"
            value={ControleConsulta?.telefone}
            className="form-control"
            id="inputTelefone"
            disabled
          />
        </div>

        <div className="col-md-4">
          <label htmlFor="inputEmail" className="form-label">
            Email
          </label>
          <input
            type="text"
            name="email"
            value={ControleConsulta?.email}
            className="form-control"
            id="inputEmail"
            disabled
          />
        </div>

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

        <div className="col-md-3">
          <label htmlFor="inputCrmEstado" className="form-label">
            CRM-UF
          </label>
          <input
            type="text"
            name="crm_estado"
            value={ControleConsulta?.crm_estado}
            className="form-control"
            id="inputCrmEstado"
            disabled
          />
        </div>

        <div className="col-md-3">
          <label htmlFor="inputCrmNum" className="form-label">
            nº CRM
          </label>
          <input
            type="text"
            name="crm_numero"
            value={ControleConsulta?.crm_num}
            className="form-control"
            id="inputCrmNum"
            disabled
          />
        </div>
      </form>
    </>
  );
};

/*function setIsChecked(checked: any) {
  throw new Error("Function not implemented.");
}
*/
