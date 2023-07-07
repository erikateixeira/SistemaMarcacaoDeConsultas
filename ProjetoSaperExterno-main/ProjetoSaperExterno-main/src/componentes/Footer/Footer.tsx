import { Row } from 'react-bootstrap';
import './Footer.css';
export const Footer = () => {
  return (
    <Row>
      <div className="footer">
        <footer class="footer-top-wrap">
          <div class="container">
            <div class="row">
              <div class="col-sm">
                <small>
                  Horário de funcionamento: Segunda a Sexta - 08h às 18h
                </small>
              </div>
              <div class="col-sm">
                <small>
                  Desenvolvido por: Equipe Saper - Erika, Débora, Hellry
                </small>
              </div>
              <div class="col-sm">
                <small>
                  Contatos: +55(85)99623-0391/ +55(88)98122-3453/
                  +55(11)94947-6966
                </small>
              </div>
            </div>
          </div>
        </footer>
      </div>
    </Row>
  );
};
