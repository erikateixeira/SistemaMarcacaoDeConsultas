import { useEffect } from "react";
import { Cabecalho } from "./Componentes/Cabecalho";
export const PaginaMedico = () => {
  useEffect(() => {
    document.title = "Página Médico";
  }, []);
  return (
    <>
      <Cabecalho nomeTela="Página Médico"></Cabecalho>

      <div className="row">
        <div className="col-md-12">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            <a href="ControleMedico">Controle Medico</a>
          </button>
        </div>
      </div>

      <div className="col-md-12">
        <button id="btncontato" className="btn btn-info rounded-pill px-3">
          Log out
        </button>
      </div>
    </>
  );
};
