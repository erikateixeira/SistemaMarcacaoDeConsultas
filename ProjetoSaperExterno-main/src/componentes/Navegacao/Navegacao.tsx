import './Navegacao.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';


export const Navegacao = () => {
  return (
    <>
      <nav className="navbar navbar-expand-sm custom-navbar navbar-dark">
        <div className="container-fluid align-items-center">

          <div className="d-flex justify-content-center align-items-center">
            <svg width="200" height="200">
              <a className="navbar-brand" href="/">
                <image href="src/assets/img/logo.jpg" height="200" width="200" className="rounded-pill" />
              </a>
            </svg>
          </div>

          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
            <ul className="navbar-nav">
              <li className="nav-item">
                <a className="nav-link" href="/">Home</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/sobre">Sobre a Empresa</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/especialidades">Especialidades</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/convenios">Convênios</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/contato">Contato</a>
              </li>
              <li className="nav-item">
                <a className="whatsapp-button" href="https://api.whatsapp.com/send?phone=5511949476966" target="_blank"  ><i className="fab fa-whatsapp"></i>Agendar agora</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
};