import './Apresentacao.css';
import { Container, Row, Col } from 'react-bootstrap';

export const Apresentacao = () => {
  return (

    <div className="apresentacao">
      <div className="imagem">
        <img src="src/assets/img/home.jpg" className="id" alt="Imagem Home" />
      </div>
      <h2>
        Pensou em Medicina, pensou em
        <div className="logo-container">
          <img src="src/assets/img/fundo.png" className="logo" alt="Logo" />
        </div>
      </h2>
      <p>
        Sempre buscando estabelecer relações duradouras, proporcionando um
        atendimento personalizado e de qualidade excepcional, com indicações
        precisas de diagnósticos.
        <br />
        <br />
        A Clínica Première Santé foi fundada em julho de 2023, com um excelente
        corpo clínico formado por médicos de diferentes estados, atendendo as
        especialidades Cardiologia, Dermatologia e Ginecologia e possibilitando
        o acesso a diversos convênios.
      </p>
      <h2>Prontuário na palma da sua mão!</h2>
      <p>
        Receba o seu Prontuário impresso direto pelo médico, com todas as
        informações da consulta de forma simplificada e segura para manter o seu próprio histórico.
      </p>
      <h2>Nossas especialidades</h2>
      <Container>
        <Row>
          <Col>
            <h5>Cardiologista</h5>
            <p>
              Cardiologia é a especialidade médica que ocupa do diagnóstico e
              tratamento das doenças que acometem o coração bem como os outros
              componentes do sistema circulatório.
            </p>
          </Col>
          <Col>
            <h5>Dermatologista</h5>
            <p>
              O dermatologista é o médico que se especializou na prevenção, no
              diagnóstico e no tratamento de doenças e condições que afetam a
              pele, o cabelo, as unhas e as mucosas, totalizando mais de 3 mil
              problemas diferentes.
            </p>
          </Col>
          <Col>
            <h5>Ginecologista</h5>
            <p>
              A ginecologia é um ramo da medicina. Sendo uma especialidade
              própria para estudar, cuidar e tratar a saúde do aparelho
              reprodutor feminino.
            </p>
          </Col>
        </Row>
      </Container>
      <h2>Convênios</h2>
      <p>
        Oferecemos uma parceria com Unimed, São Camilo, Camed, Bradesco Saúde, Famed,
        Cassi, Issec e Life Empresarial Saúde para melhor atender nossos clientes e 
        proporcionar um atendimento de qualidade e com precisão.
      </p>

      <Container className='container'>
        <div>
          <h2>
            Depoimentos sobre os profissionais da
            <div className="logo-container">
              <img src="src/assets/img/fundo.png" className="logo" alt="Logo" />
            </div>
          </h2>
        </div>
        <Row className='justify-content-between'>
          <Col>
            <div className="depoimento">
              <div className="avaliacao">
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
              </div>
              <h6 className='medico'>Dr. Bruno Andrade dos Santos</h6>
              <p className='comentario'>
                <i><q>O Dr. Bruno foi muito atencioso durante a consulta e observou meus sintomas com precisão. Com certeza farei acompanhamento com ele.</q></i>
              </p>
              <h6 className='paciente'>Francisco Mauro Teixeira</h6>
            </div>
          </Col>
          <Col>
            <div className="depoimento">
              <div className="avaliacao">
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
              </div>
              <h6 className='medico'>Dra. Marisa Aguiar Oliveira</h6>
              <p className='comentario'>
                <i><q>Minha experiência foi excepcional! Tratamento humanizado, eficiente e pontual pela Dra. Marisa Aguiar Oliveira.</q></i>
              </p>
              <h6 className='paciente'>Pedro Jorge da Silva</h6>
            </div>
          </Col>
          <Col>
            <div className="depoimento">
              <div className="avaliacao">
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
                <span className="estrela">&#9733;</span>
              </div>
              <h6 className='medico'> Dra. Bianca Medeiros Fontes </h6>
              <p className='comentario'>
                <i><q>A Dra. Bianca demonstrou muito profissionalismo, empatia, e foi muito atenciosa, me deixando muito confortável e passando segurança!</q></i>
              </p>
              <h6 className='paciente'>Pamela da Costa Martins</h6>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};
