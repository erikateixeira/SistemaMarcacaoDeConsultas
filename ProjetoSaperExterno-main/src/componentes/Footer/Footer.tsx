import { Row } from 'react-bootstrap';
import './Footer.css';

export const Footer = () => {
  return (
    <Row>
      <div className="footer">
        <footer className="footer-top-wrap">
          <Row className="justify-content-between align-items-center">
            <div className='col-sm'>
              <img src="src/assets/img/logo.jpg" className='logo' alt="Logo" />
            </div>
            <div className='col-sm'>
              <p className='infos'><b>Horário de funcionamento:</b> Segunda a Sexta - 08h às 18h<br />
                Rua Leonardo Medeiros Tauá, 67 - Mineiras - Fortaleza/CE<br />
                <b>Contatos:</b> +55(85)99623-0391 / +55(88)98122-3453 / +55(11)94947-6966</p>
            </div>
            <div className='col-sm'>
              <p className='creators'>&copy; 2023–2023 <br />
                <small><b>Desenvolvido por:</b> Equipe Saper - Erika, Débora, Hellry</small>
              </p>
            </div>
          </Row>
        </footer>
      </div>
    </Row>
  );
};
