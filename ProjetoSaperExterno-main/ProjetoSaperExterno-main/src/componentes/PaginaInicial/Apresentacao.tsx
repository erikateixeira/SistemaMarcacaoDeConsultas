import './Apresentacao.css';
export const Apresentacao = () => {
  return (
    <div className="apresentacao">
      <div className="imagem">
        <img src="src/assets/img/home.jpg" className="id" />
      </div>

      <h2>
        Pensou em Medicina, pensou em
        <img src="src/assets/img/fundo.png" className="logo" />
      </h2>
      <p>
        Sempre buscando estabelecer relações duradouras, proporcionando um
        atendimento personalizado e de qualidade excepcional, com indicações
        precisas de diagnósticos.
      </p>

      <p>
        A Clínica Première Santé foi fundada em julho de 2023, com um excelente
        corpo clínico formado por médicos de diferentes estados, atendendo as
        especialidades Cardiologia, Dermatologia e Ginecologia e possibilitando
        o acesso a diversos convênios.
      </p>
      <h2>Prontuário Eletrônico!</h2>
      <p>
        Receba o Prontuário Eletrônico direto no seu e-mail, com todas as
        informações da consulta de forma simplificada e segura.
      </p>

      <h2>Nossas especialidades</h2>
      <div class="container">
        <div class="row">
          <div class="col-sm">
            <h5>Cardiologista</h5>
            <p>
              Cardiologia é a especialidade médica que ocupa do diagnóstico e
              tratamento das doenças que acometem o coração bem como os outros
              componentes do sistema circulatório.
            </p>
          </div>
          <div class="col-sm">
            <h5>Dermatologista</h5>
            <p>
              O dermatologista é o médico que se especializou na prevenção, no
              diagnóstico e no tratamento de doenças e condições que afetam a
              pele, o cabelo, as unhas e as mucosas, totalizando mais de 3 mil
              problemas diferentes.
            </p>
          </div>
          <div class="col-sm">
            <h5>Ginecologista</h5>
            <p>
              A ginecologia é um ramo da medicina. Sendo uma especialidade
              própria para estudar, cuidar e tratar a saúde do aparelho
              reprodutor feminino.
            </p>
          </div>
        </div>
      </div>
      <h2>Convênios</h2>
      <p>
        Oferecemos uma parceria com Unimed, São Camilo, Camed, Bradesco Saúde,
        para melhor atender nossos clientes e proporcionar um atendimento de
        qualidade e com precisão.
      </p>

      <h2>
        Depoimentos sobre os profissionais da{' '}
        <img src="src/assets/img/fundo.png" className="logo" />
      </h2>
      <div class="container">
        <div class="row">
          <div class="col-sm">
            <h6>Francisco Mauro Teixeira</h6>
            <p>
              O Dr. Bruno foi muito atencioso durante a consulta e observou meus
              sintomas com precisão. Com certeza farei acompanhamento com ele.
            </p>
          </div>
          <div class="col-sm">
            <h6>Pedro Jorge da Silva</h6>
            <p>
              Minha experiência foi excepcional! Tratamento humanizado,
              eficiente e pontual pela Dra. Marisa Aguiar Oliveira
            </p>
          </div>
          <div class="col-sm">
            <h6>Pamela da Costa Martins</h6>
            <p>
              A Dra. Bianca demonstrou muito profissionalismo, empatia, e foi
              muito atenciosa, me deixando muito confortável e passando
              segurança!
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
