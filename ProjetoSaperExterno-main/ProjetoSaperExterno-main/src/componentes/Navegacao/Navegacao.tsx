import { Row } from 'react-bootstrap';
import './Navegacao.css';

import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

export const Navegacao = () => {
  return (
    <Row>
      <div className="navegacao">
        <div>
          <svg width="200" height="200">
            <a href="/">
              <image href="src/assets/img/logo.jpg" height="200" width="200" />
            </a>
          </svg>
        </div>

        <div className="home">
          <a href="/">Home</a>
        </div>

        <div className="sobre">
          <a href="/sobre">Sobre a Empresa</a>
        </div>

        <div className="especialidades">
          <a href="/especialidades">Especialidades</a>
        </div>

        <div className="convenios">
          <a href="/convenios">Convênios</a>
        </div>

        <div className="contato">
          <a href="/contato">Contato</a>
        </div>

        <a
          href="https://api.whatsapp.com/send?phone=5511949476966"
          target="_blank"
          className="whatsapp-button"
        >
          <i className="fab fa-whatsapp"></i> Agendar agora
        </a>
      </div>
    </Row>
  );
};
