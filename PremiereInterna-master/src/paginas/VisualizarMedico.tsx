import moment from "moment";
import {
  ChangeEvent,
  JSXElementConstructor,
  Key,
  ReactElement,
  ReactNode,
  ReactPortal,
  useEffect,
  useState,
} from "react";
import { Cabecalho } from "./Componentes/Cabecalho";
import { IMedico } from "../Models/IMedico";
import {
  useLocation,
  useParams,
  useNavigate,
  Navigate,
} from "react-router-dom";
import { Service } from "../Service";

export const VisualizarMedico = () => {
  const { nome } = useParams();
  const location = useLocation();
  const Navigate = useNavigate();
  const [medico, setMedico] = useState<IMedico>({
    diasDisponiveis: [] as string[],
  });

  useEffect(() => {
    document.title = "Dados do Médico";
    Service.getMedicosPorNome(nome || "").then((result) => {
      setMedico(result.data[0]);
    });
  }, []);

  return (
    <>
      <Cabecalho nomeTela="Dados Médico"></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Cadastro do Médico</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-6">
          <label htmlFor="input disabledNome" className="form-label">
            Nome completo
          </label>
          <input
            disabled
            maxLength={80}
            minLength={10}
            name="nome"
            type="text"
            className="form-control"
            id="input disabledNome"
            value={medico?.nome}
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="input disabledCnpj" className="form-label">
            CNPJ
          </label>
          <input
            disabled
            maxLength={14}
            minLength={14}
            name="cnpj"
            type="text"
            className="form-control"
            id="input disabledCnpj"
            value={medico?.cnpj}
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="input disabledCrm" className="form-label">
            Número CRM
          </label>
          <input
            disabled
            maxLength={6}
            minLength={6}
            name="crm_num"
            type="number"
            value={medico?.crm_num}
            className="form-control"
            id="input disabledCrm"
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="input disabledCrmEstado" className="form-label">
            Estado CRM
          </label>

          <select
            name="crm_estado"
            value={medico?.crm_estado}
            className="form-control"
            id="input disabledCrmEstado"
          >
            <option>Selecionar...</option>
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
        <div className="col-2">
          <label htmlFor="input disabledelefone" className="form-label">
            Telefone
          </label>
          <input
            disabled
            name="telefone"
            type="text"
            value={medico?.telefone}
            className="form-control"
            id="input disabledtelefone"
          />
        </div>

        <div className="col-5">
          <label htmlFor="input disabledemail" className="form-label">
            Email
          </label>
          <input
            disabled
            name="email"
            type="email"
            value={medico?.email}
            className="form-control"
            id="input disabledemail"
          />
        </div>

        <div className="col-md-3">
          <label htmlFor="input disabledEspecialidade" className="form-label">
            Especialidade
          </label>
          <select
            name="especialidade"
            value={medico?.especialidade}
            className="form-control"
            id="input disabledEspecialidade"
          >
            <option>Selecionar...</option>
            <option>CARDIOLOGISTA</option>
            <option>DERMATOLOGISTA</option>
            <option>GINECOLOGISTA</option>
          </select>
        </div>

        <div className="col-2">
          <label htmlFor="input disabledNumeroSala" className="form-label">
            Número Sala
          </label>
          <select
            name="sala"
            value={medico?.sala}
            className="form-control"
            id="input disabledNumeroSala"
          >
            <option>Selecionar...</option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
          </select>
        </div>
        <div className="col-3">
          <label htmlFor="input disabledLogin" className="form-label">
            Login
          </label>
          <input
            disabled
            maxLength={30}
            name="login"
            type="text"
            value={medico?.login}
            className="form-control"
            id="input disabledLogin"
          />
        </div>

        <div className="col-2">
          <label htmlFor="input disabledSenha" className="form-label">
            Senha
          </label>
          <input
            disabled
            maxLength={15}
            name="senha"
            type="password"
            className="form-control"
            id="input disabledSenha"
          />
        </div>

        <div className="col-2">
          <label
            htmlFor="input disabledHoraDisponivelInicial"
            className="form-label"
          >
            Hora Disponível Inicial{" "}
          </label>
          <input
            disabled
            name="hora_inicial"
            type="time"
            value={medico?.hora_inicial}
            step="1"
            className="form-control"
            id="input disabledHoraDisponivelInicial"
          />
        </div>
        <div className="col-3">
          <label
            htmlFor="input disabledHoraDisponivelFinal"
            className="form-label"
          >
            Hora Disponível Final
          </label>
          <input
            disabled
            name="hora_final"
            type="time"
            value={medico?.hora_final}
            step="1"
            className="form-control"
            id="input disabledHoraDisponivelFinal"
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="input disabledValorConsulta" className="form-label">
            Valor Consulta
          </label>
          <input
            disabled
            name="valor_consulta"
            type="number"
            value={medico?.valor_consulta}
            className="form-control"
            id="input disabledValorConsulta"
          />
        </div>
        <div className="col-12">
          <label htmlFor="input disabledDiasDisponiveis" className="form-label">
            Dias das Semanas Disponíveis
          </label>
          <input
            disabled
            name="diasDisponiveis"
            type="text"
            value={
              medico?.diasDisponiveis ? medico.diasDisponiveis.join(", ") : ""
            }
            className="form-control"
            id="input disabledDiasDisponiveis"
            placeholder="SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA"
          />
        </div>

        <div className="col-12">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={() => Navigate(-1)}
            type="button"
          >
            Voltar
          </button>
        </div>
      </form>
    </>
  );
};
