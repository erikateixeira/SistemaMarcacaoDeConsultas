import { Link } from 'react-router-dom';
import './Especialidades.css';
import { Container, Row, Col } from 'react-bootstrap';


export const PaginaEspecialidadesCardiologia = () => {
  return (
    <div className="especialidades">

      <div className="col-sm">
        <h3>Cardiologia</h3>
        <p>
          Cardiologia é a especialidade médica que ocupa do diagnóstico e
          tratamento das doenças que acometem o coração bem como os outros
          componentes do sistema circulatório.
        </p>
      </div>

      <Container>

        <Row className="line">
          <Col className="text-center">
            <p className="destaque">
              Dr. Bruno Andrade dos Santos <br />
              CRM CE-257456
            </p>
            <img className="doc1" src="src/assets/img/doctor1.png" alt="Doctor 1" />
          </Col>
          <Col className="text-center">
            <p className="destaque">
              Dra. Mariana Bezerra Araújo Freitas <br /> CRM MA-145782
            </p>
            <img className="doc1" src="src/assets/img/doctor3.png" alt="Doctor 3" />
          </Col>
        </Row>
      </Container>


      <div className='rodape'>
        <Link to="/especialidades" className='button-link'>
          <h3>Voltar para Especialidades</h3>
        </Link>
      </div>

    </div>
  );
};
