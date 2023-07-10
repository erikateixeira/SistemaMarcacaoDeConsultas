import Logo from "../../assets/Première Santé.png";
import "../Css/Cabecalho.css";
export const Cabecalho = (props: any) => {
  return (
    <>
      <div className="row">
        <div className="col-2">
          <a href="/admin">
            <img src={Logo} alt="Logo do Site" title="Logo do Site" />
          </a>
        </div>

        <div className="col-10">{props.nomeTela}</div>
      </div>
    </>
  );
};

export default Cabecalho;
