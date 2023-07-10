import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/Recepcionista.css";
export const Recepcionista = () => {
  return (
    <>
      <Cabecalho nomeTela="Recepcionista"></Cabecalho>
      <div className="row">
        <div className="col-12">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            <a href="Funcionarios">Dados do Funcionário</a>
          </button>
        </div>

        <div className="col-12">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            <a href="Medico">Dados do médico</a>
          </button>
        </div>

        <div className="col-12">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            <a href="Paciente">Dados do Paciente</a>
          </button>
        </div>

        <div className="col-12">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            <a href="ControleConsulta">Controle da Consulta</a>
          </button>
        </div>
      </div>

      <div className="row">
        <div className="col-12">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            <a href="/">Log Out</a>
          </button>
        </div>
      </div>
    </>
  );
};
