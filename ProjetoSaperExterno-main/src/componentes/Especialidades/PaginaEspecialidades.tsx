import { Link } from 'react-router-dom';
import './PaginaEspecialidades.css';
import { Container, Row, Col } from 'react-bootstrap';


export const PaginaEspecialidades = () => {
  return (
    <div className="especialidades">
      <div className='topo'>
        <h2>Nossas especialidades</h2>
        <p className="p2">
          Nossa equipe é formada por profissionais qualificados comprometidos com um atendimento eficiente.
        </p>
      </div>
      <Container>
        <Row>
          <Col className='container'>
            <Link to="/cardiologia" className='button-link'>
              <h3>Cardiologista</h3>
            </Link>
            <img src="src/assets/img/cardiologist_symbol0.png" className='symbol' />
          </Col>
          <Col>
            <Link to="/dermatologia" className='button-link'>
              <h3>Dermatologista</h3>
            </Link>
            <img src="src/assets/img/dermatologist_symbol.png" className='symbol' />
          </Col>

          <Col>
            <Link to="/ginecologia" className='button-link'>
              <h3>Ginecologista</h3>
            </Link>
            <img src="src/assets/img/ginecologist_symbol.png" className='symbol' />
          </Col>

        </Row>
      </Container>
    </div>
  );
};
