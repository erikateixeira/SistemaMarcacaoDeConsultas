import { Link } from 'react-router-dom';
import './Especialidades.css';
import { Container, Row, Col } from 'react-bootstrap';


export const PaginaEspecialidadesDermatologia = () => {
  return (
    <div className="especialidades">

      <div className="col-sm">
        <h3>Dermatologia</h3>
        <p>
          O dermatologista é o médico que se especializou na prevenção, no
          diagnóstico e no tratamento de doenças e condições que afetam a
          pele, o cabelo, as unhas e as mucosas, totalizando mais de 3 mil
          problemas diferentes.
        </p>
      </div>

      <Container>
        <Row className='line'>
          <Col>
            <div className="col-sm">
              <p className="destaque">
                Dra. Marisa Aguiar Oliveira
                <br />
                CRM CE-472843
              </p>
              <img className="doc1" src="src/assets/img/doctor7.png" />
            </div>
          </Col>
          <Col>
            <div className="col-sm">
              <p className="destaque">
                Dr. Paulo Nogueira de Costa Lima
                <br /> CRM PB-758142
              </p>
              <img className="doc1" src="src/assets/img/doctor2.png" />
            </div>
          </Col>
        </Row>
      </Container>

      <div className='rodape'>
        <Link to="/especialidades" className='button-link'>
          <h3>Voltar para Especialidades</h3>
        </Link>
      </div>

    </div >
  );
};
