import { Link } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import './Especialidades.css';

export const PaginaEspecialidadesGinecologia = () => {
    return (
        <div className="especialidades">

            <div className="col-sm">
                <a>
                    <h3>Ginecologia</h3>
                </a>
                <p>
                    A ginecologia é um ramo da medicina. Sendo uma especialidade
                    própria para estudar, cuidar e tratar a saúde do aparelho
                    reprodutor feminino.
                </p>
            </div>

            <Container>
                <Row className='line'>
                    <Col>
                        <div className="row">
                            <div className="col-sm">
                                <p className="destaque">
                                    Dr. Adriano de Mello Soares
                                    <br />
                                    CRM PI-785426
                                </p>
                                <img className="doc1" src="src/assets/img/doctor4.png" />
                            </div>
                        </div>
                    </Col>
                    <Col>
                        <div className="row">
                            <div className="col-sm">

                                <p className="destaque">
                                    Dra. Bianca Medeiros Fontes
                                    <br /> CRM CE-123456
                                </p>
                                <img className="doc1" src="src/assets/img/doctor6.png" />
                            </div>
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