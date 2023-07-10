import "../Css/Linha.css";
export const Linha = () => {
  return (
    <>
      <div className="row">
        <div className="col-6">Paciente</div>
        <div className="col-2">Hora</div>
        <div className="col-4">
          <button id="btncontato" className="btn btn-info rounded-pill px-3">
            Registrar
          </button>
        </div>
      </div>
    </>
  );
};
